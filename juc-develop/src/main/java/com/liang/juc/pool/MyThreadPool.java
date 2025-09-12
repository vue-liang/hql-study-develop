package com.liang.juc.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class MyThreadPool {
    public static void main(String[] args) {
        MyThreadExecutor threadExecutor = new MyThreadExecutor
                (4, 5, 1000, TimeUnit.MILLISECONDS,
                        new MyBlockingQueue<>(1), (queue, task) -> {
                    // 1. 死等
//            queue.put(task);
                    // 2) 带超时等待
//            queue.offer(task, 1500, TimeUnit.MILLISECONDS);
                    // 3) 让调用者放弃任务执行
                    log.debug("放弃{}", task);
                    // 4) 让调用者抛出异常
//            throw new RuntimeException("任务执行失败 " + task);
                    // 5) 让调用者自己执行任务
//                    task.run();
                });
        for (int i = 0; i < 8; i++) {
            int j = i;
            threadExecutor.execute(() -> {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("{}", "\t ---执行任务" + j + "完成");
            });
        }
    }
}

@FunctionalInterface
interface RejectedPolicy<T> {
    void reject(MyBlockingQueue<T> queue, T task);
}

class MyThreadExecutor {

    private final int corePoolSize;

    private int maximumPoolSize;

    private long keepAliveTime;

    private TimeUnit unit;

    private MyBlockingQueue<Runnable> workQueue;

    private RejectedPolicy<Runnable> rejectedPolicy;

    private HashSet<Worker> workers = new HashSet<>();

    public MyThreadExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, MyBlockingQueue<Runnable> workQueue, RejectedPolicy<Runnable> rejectedPolicy) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.unit = unit;
        this.workQueue = workQueue;
        this.rejectedPolicy = rejectedPolicy;
    }

    public void execute(Runnable task) {
        synchronized (workQueue) {
            if (workers.size() < corePoolSize) { // 创建核心线程
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
            } else if (workQueue.size() < workQueue.capacity) { // 加入任务队列
                workQueue.put(task);
            } else if (workers.size() < maximumPoolSize) { // 创建临时线程
                Worker worker = new Worker(task, true);
                workers.add(worker);
                worker.start();
            } else {
                workQueue.tryPut(rejectedPolicy, task);
            }
        }
    }

    private static AtomicInteger i = new AtomicInteger(0);

    private class Worker extends Thread {

        private Runnable task;

        private Boolean working = false;

        public Worker(Runnable task) {
            this.task = task;
        }

        public Worker(Runnable task, Boolean working) {
            this.task = task;
            this.working = working;
        }

        @Override
        public void run() {
            // 阻塞获取
            if (!working) {
                Thread.currentThread().setName("核心线程" + i.getAndIncrement());
                while (task != null || (task = workQueue.take()) != null) {
                    try {
                        task.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        task = null;
                    }
                }
            } else {
                Thread.currentThread().setName("临时线程" + i.getAndIncrement());
                while (task != null || (task = workQueue.poll(keepAliveTime, unit)) != null) {
                    try {
                        task.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        task = null;
                    }
                }
            }
            synchronized (workQueue) {
                workers.remove(this);
            }
        }
    }
}

@Slf4j
class MyBlockingQueue<E> {

    int capacity;

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition notEmpty = lock.newCondition();

    private final Condition notFull = lock.newCondition();

    private final Deque<E> queue = new ArrayDeque<E>();

    public MyBlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("阻塞队列容量不得小于1");
        }
        this.capacity = capacity;
    }

    public int size() {
        try {
            lock.lock();
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    public void put(E t) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.addLast(t);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            E t = queue.removeFirst();
            notFull.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public boolean offer(E e, long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while (queue.size() == capacity) {
                if (nanos <= 0) {
                    return false;
                }
                try {
                    nanos = notFull.awaitNanos(nanos);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            queue.addLast(e);
            notEmpty.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    public E poll(long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while (queue.isEmpty()) {
                if (nanos <= 0) {
                    return null;
                }
                try {
                    nanos = notEmpty.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            E t = queue.removeFirst();
            notFull.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectedPolicy<E> rejectedPolicy, E task) {
        synchronized (queue) {
            log.info("拒绝策略逻辑");
            rejectedPolicy.reject(this, task);
        }
    }

}

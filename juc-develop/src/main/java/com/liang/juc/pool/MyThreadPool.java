package com.liang.juc.pool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPool {
    public static void main(String[] args) {
        MyThreadExecutor threadExecutor = new MyThreadExecutor
                (4,2,1000,TimeUnit.MILLISECONDS,
                        new MyBlockingQueue<>(5));
        for (int i = 0; i < 4; i++) {
            int j = i;
            threadExecutor.execute(() -> {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + j);
            });
        }
    }
}

class MyThreadExecutor{

    int corePoolSize;

    int maximumPoolSize;

    long keepAliveTime;

    TimeUnit unit;

    MyBlockingQueue<Runnable> workQueue;

    HashSet<Worker> workers = new HashSet<>();

    public MyThreadExecutor(int corePoolSize, int maximumPoolSize,long keepAliveTime, TimeUnit unit, MyBlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.unit = unit;
        this.workQueue = workQueue;
    }

    public void execute(Runnable task){
        synchronized (workQueue){
            if(workers.size()<corePoolSize){
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
            }else {
                workQueue.put(task);
            }
        }
    }

    private static AtomicInteger i = new AtomicInteger(0);

    private class Worker extends Thread {

        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            Thread.currentThread().setName("线程"+i.getAndIncrement());
            // 阻塞获取
            while(task!=null||(task=workQueue.take())!=null){
                try{
                    task.run();
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    task=null;
                }
            }
            synchronized (workQueue){
                workers.remove(this);
            }
        }
    }
}

class MyBlockingQueue<E>{

    int capacity;

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition notEmpty = lock.newCondition();

    private final Condition notFull = lock.newCondition();

    private final Deque<E> queue = new ArrayDeque<E>();

    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(E t) {
        lock.lock();
        try{
            while(queue.size() == capacity){
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.addLast(t);
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }
    public E take() {
        lock.lock();
        try{
            while(queue.isEmpty()){
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            E t = queue.removeFirst();
            notFull.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    public boolean offer(E e, long timeout, TimeUnit unit){
        lock.lock();
        try{
            long nanos = unit.toNanos(timeout);
            while(queue.size() == capacity){
                if(nanos <= 0){
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
        }finally {
            lock.unlock();
        }
    }

    public E poll(long timeout, TimeUnit unit) {
        lock.lock();
        try{
            long nanos = unit.toNanos(timeout);
            while(queue.isEmpty()){
                if(nanos <= 0){
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
        }finally {
            lock.unlock();
        }
    }

}

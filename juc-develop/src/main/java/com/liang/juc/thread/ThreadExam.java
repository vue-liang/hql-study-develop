package com.liang.juc.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

// 多线程交替输出数字
public class ThreadExam {
    private static final AtomicInteger atomicInteger = new AtomicInteger(0);
    private static Integer count = 0;
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (count) {
                        if(count == 20) {
                            break;
                        }
                        System.out.println(Thread.currentThread().getName() + ":" + count++);
                    }
                }

            },"线程"+i).start();
        }
//        MyThread myThread = new MyThread();
//        myThread.setName("t1");
//        MyThread myThread2 = new MyThread();
//        myThread2.setName("t2");
//        myThread.start();
//        myThread2.start();
        // 线程池
//        ThreadFactory threadFactory = (runnable)->{
//          Thread t = new Thread(runnable);
//          t.setName("pool-线程 t"+atomicInteger.incrementAndGet());
//          t.setDaemon(false);
//          return t;
//        };
//
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
//                2,4,
//                10,TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(5),
//                threadFactory,
//                new ThreadPoolExecutor.CallerRunsPolicy()
//        );
//        MyRunnable myRunnable = new MyRunnable();
//        MyRunnable myRunnable1 = new MyRunnable();
//        threadPoolExecutor.execute(myRunnable);
//        threadPoolExecutor.execute(myRunnable1);
//        threadPoolExecutor.shutdown();
    }
}
class MyThread extends Thread {
    private static Integer count = 0;
    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (count) {
                if (count >= 20) {
                    break;
                }
                System.out.println(Thread.currentThread().getName()+"线程打印"+count++);
            }
        }
    }
}
class MyRunnable implements Runnable {
    private static Integer count = 0;
    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (count) {
                if (count >= 20) {
                    break;
                }
                System.out.println(Thread.currentThread().getName()+"线程打印"+count++);
            }
        }
    }
}

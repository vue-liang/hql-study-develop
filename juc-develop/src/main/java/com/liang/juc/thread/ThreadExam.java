package com.liang.juc.thread;

import java.util.concurrent.TimeUnit;

// 多线程交替输出数字
public class ThreadExam {

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                synchronized (count) {
//                    System.out.println(Thread.currentThread().getName() + ":" + count++);
//                }
//            },"线程"+i).start();
//        }
        MyThread myThread = new MyThread();
        myThread.setName("t1");
        MyThread myThread2 = new MyThread();
        myThread2.setName("t2");
        myThread.start();
        myThread2.start();
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

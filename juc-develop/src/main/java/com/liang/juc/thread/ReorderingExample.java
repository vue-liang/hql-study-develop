package com.liang.juc.thread;

public class ReorderingExample {
    private static boolean ready = false;
    private static int number = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread readerThread = new Thread(() -> {
            while (!ready) {
                Thread.yield(); // 等待 ready 变为 true
            }
            System.out.println("number = " + number); // 期望输出 42
        });

        Thread writerThread = new Thread(() -> {
            number = 42;          // 写操作1
            ready = true;          // 写操作2 该指令可能会提前
        });

        readerThread.start();
        writerThread.start();

        readerThread.join();
        writerThread.join();
    }
}

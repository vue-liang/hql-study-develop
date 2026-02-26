package com.liang.juc.thread;

import java.util.concurrent.*;

/**
 * @author liang
 * @create 2026-02-25 10:19
 * @description 创建线程
 */
public class ThreadCreate {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // new
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                System.out.println("t1---run");
            }
        };
        // runnable
        Thread t2 = new Thread(()-> System.out.println("t2---run"),"t2");
        // task callable
        FutureTask<String> task = new FutureTask<>(() -> {
            System.out.println("t3---run");
            return "result";
        });

        Thread t3 = new Thread(task);

        ExecutorService executors = Executors.newFixedThreadPool(3);

        Future<String> task1 = executors.submit(() -> {
            System.out.println("t4---run");
            return "result";
        });

        t1.start();
        t2.start();
        t3.start();

        System.out.println(task.get());
        System.out.println(task1.get());

        executors.shutdown();
    }
}

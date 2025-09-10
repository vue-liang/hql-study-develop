package com.liang.juc.exam;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * |-->B
 * A -       D
 * |--C
 */
public class CompletableExam1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        long start = System.currentTimeMillis();
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "开始执行");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Task A";
        }, executorService);
        CompletableFuture<String> completableFuture2 = completableFuture1
                .thenApplyAsync(resA ->
                {
                    System.out.println(Thread.currentThread().getName() + "开始执行");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "Task B接到了" + resA;
                }, executorService);
        CompletableFuture<String> completableFuture3 = completableFuture1
                .thenApplyAsync(resA -> {
                    System.out.println(Thread.currentThread().getName() + "开始执行");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "Task C接到了" + resA;
                }, executorService);

        CompletableFuture.allOf(completableFuture2, completableFuture3)
                .thenRun(() -> {
                    System.out.println(Thread.currentThread().getName() + "开始执行");
                    System.out.println(completableFuture2.join());
                    System.out.println(completableFuture3.join());
                })
                .join();
        long end = System.currentTimeMillis();
        System.out.println("消耗时间"+(end - start)+"ms");
        executorService.shutdown();

    }
}

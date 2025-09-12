package com.liang.juc.exam;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * |-->B
 * A -       D
 * |--C
 */
@Slf4j
public class CompletableExam1 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        long start = System.currentTimeMillis();
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            log.info("开始执行Task A");
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
                    log.info("开始执行Task B");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "Task B接到了" + resA;
                }, executorService);
        CompletableFuture<String> completableFuture3 = completableFuture1
                .thenApplyAsync(resA -> {
                    log.info("开始执行Task C");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "Task C接到了" + resA;
                }, executorService);
        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(completableFuture2, completableFuture3)
                .thenRunAsync(() -> {
                    System.out.println(completableFuture2.join());
                    System.out.println(completableFuture3.join());
                    log.info("开始执行Task D");
                }, executorService);
//        CompletableFuture.supplyAsync(() -> {
//            completableFuture.join();
//            try {
//                TimeUnit.SECONDS.sleep(2);
//                return LocalDateTime.now() + "\t" + "所有Task执行完毕";
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }, executorService).join();
        completableFuture.thenRunAsync(() -> {
            log.info("所有Task执行完毕");
        }, executorService).join();
//        completableFuture.join();
//        System.out.println(res);
        long end = System.currentTimeMillis();
        System.out.println("消耗时间" + (end - start) + "ms");
        executorService.shutdown();

    }
}

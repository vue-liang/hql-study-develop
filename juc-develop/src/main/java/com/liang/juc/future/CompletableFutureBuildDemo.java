package com.liang.juc.future;

import java.util.concurrent.*;

public class CompletableFutureBuildDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            int result = ThreadLocalRandom.current().nextInt(100);
            System.out.println(Thread.currentThread().getName() + "----- 加入");
            try {TimeUnit.SECONDS.sleep(1);} catch (Exception e) {e.printStackTrace();}
            System.out.println("1秒后得出结果+【" + result + "】");
            if(result>=50) throw new RuntimeException("故意的异常");
            return result;
        },executorService).whenComplete((result, e) -> {
            if (e == null) {
                System.out.println("无异常，结果为：" + result);
            }
        }).exceptionally(throwable -> {
            throwable.printStackTrace();
            System.out.println("异常" + throwable.getMessage());
            return null;
        });
        System.out.println(Thread.currentThread().getName()+"----- 忙");

        executorService.shutdown();
        // 获取结果与触发
//        System.out.println(completableFuture.get());
    }


    public static void future1(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            int result = ThreadLocalRandom.current().nextInt(100);
            System.out.println(Thread.currentThread().getName()+"----- 加入");
            try {TimeUnit.SECONDS.sleep(1);}catch (Exception e) {e.printStackTrace();}
            System.out.println("1秒后得出结果+【"+result+"】");
            return result;
        });

        System.out.println(Thread.currentThread().getName()+"----- 忙");

        System.out.println(completableFuture.get());
    }

    // 入门
    public static void main0(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
//                3,5,6,
//                TimeUnit.SECONDS,new LinkedBlockingDeque<>(2)
//        );
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()+"----- 加入");
            try {TimeUnit.SECONDS.sleep(1);}catch (Exception e) {e.printStackTrace();}
            return "hello";
        }, executorService);

        System.out.println(completableFuture.get());

        executorService.shutdown();
        // 获取结果与触发
        System.out.println(completableFuture.get());
        // 立即获取 如果没 则 默认值
//        System.out.println(completableFuture.getNow(23));

//        System.out.println(completableFuture.join());
        // 用于判断是计算值还是括号值 true false
//        System.out.println(completableFuture.complete(23)+"\t"+completableFuture.join());
    }
}

package com.liang.juc.future;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureAPI1Demo {
    public static void main(String[] args) {
        // A执行完之后执行B
        System.out.println(CompletableFuture.supplyAsync(()->"helloA").thenRun(()->{}).join()); // null
        System.out.println(CompletableFuture.supplyAsync(()->"helloA").thenAccept(System.out::println).join()); // A null
        System.out.println(CompletableFuture.supplyAsync(()->"helloA").thenApply(r->r+"helloB").join()); // helloA+helloB

    }
}

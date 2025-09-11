package com.liang.juc.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

// 多线程交替打印数字
public class CompletableExam2 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        list.stream()
                .map(i ->
                        CompletableFuture.supplyAsync(
                                () -> "线程" + Thread.currentThread().getName() + "打印" + i, executorService
                        )
                ).collect(Collectors.toList()).stream()
                .map(CompletableFuture::join)
                .forEach(System.out::println);
        executorService.shutdown();
    }
}

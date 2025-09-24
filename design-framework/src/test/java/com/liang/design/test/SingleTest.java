package com.liang.design.test;

import com.liang.design.domain.SingleInstance;
import com.liang.design.domain.SingleInstance1;
import com.liang.design.domain.SingleInstance2;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleTest {
    @Test
    void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "--------" + SingleInstance.getInstance());
            });
        }
        executorService.shutdown();
    }

    @Test
    void test1() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "--------" + SingleInstance1.getInstance());
            });
        }
        executorService.shutdown();
    }

    @Test
    void test2() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "--------" + SingleInstance2.getInstance());
            });
        }
        executorService.shutdown();
    }
}

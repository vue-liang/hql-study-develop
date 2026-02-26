package com.liang.juc.test;

import com.liang.juc.thread.SingleInstance;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liang
 * @create 2026-02-26 11:02
 * @description
 */
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
}

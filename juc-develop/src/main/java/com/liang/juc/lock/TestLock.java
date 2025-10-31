package com.liang.juc.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestLock {
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    lock.lock();
                    log.debug("{}获取了锁", Thread.currentThread().getName());
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    log.info("{}释放了锁", Thread.currentThread().getName());
                    lock.unlock();
                }
            }, "线程" + i).start();
        }
    }
}

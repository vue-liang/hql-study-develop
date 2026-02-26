package com.liang.juc.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author liang
 * @create 2026-02-25 20:59
 * @description 线程停止
 */
public class ThreadStop {
    static volatile boolean flag = false;
    public static void main(String[] args) {
        // 1. 打断中断过程中的线程
        Thread t1 = new Thread(() -> {
            while (!flag) {
                System.out.println("t1---running---3s");
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(3L));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t1");
        // 2. 打断正常的线程
        Thread t2 = new Thread(()->{
            while(true) {
                Thread current = Thread.currentThread();
                boolean interrupted = current.isInterrupted();
                if(interrupted) {
                    System.out.println("打断状态："+interrupted);
                    break;
                }
            }
        }, "t2");

        t1.start();
        t2.start();

        try {
            System.out.println("main thread sleep 6s");
            Thread.sleep(TimeUnit.SECONDS.toMillis(6L));
            flag = true; // 关闭标志
            // stop强制关闭
//            t1.stop();
            // 异常法停止
//            t1.interrupt();
            t2.interrupt(); // 设置中断状态为true
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("end---");
    }
}

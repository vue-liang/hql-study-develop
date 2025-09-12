package com.liang.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 *  串行api
 *  thenApply() 出错不执行后续步骤 会使用之前的返回值作为参数
 *  handle() 出错不会中断 两个参数
 */
public class CompletableFutureAPIDemo {
    public static void main(String[] args) {
        // 串行编排
        CompletableFuture.supplyAsync(() -> {
            try { TimeUnit.SECONDS.sleep(1); }
            catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("111");
            return 1;
        }).thenApply(f->{
            System.out.println("222");
//            if(f>0) throw new RuntimeException("故意的");
            return f+2;
        }).thenApply(f->{
            System.out.println("333");
            return f+3;
        }).whenComplete((res, ex) -> {
            if(ex==null){
                System.out.println("--计算结果--:【"+res+"】");
            }
        }).exceptionally(e->{
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });
        // CompletableFuture.supplyAsync(() -> 1).thenApply(f-> 2).thenAccept(System.out::println);
        System.out.println(Thread.currentThread().getName()+"--忙--");
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}

package com.liang.juc.forkjoin;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
// 分治
public class TestForkJoinApi {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
//        System.out.println("结果："+forkJoinPool.invoke(new MyTask(10))+"》》》》 耗时："+(System.currentTimeMillis()-start)+"ms"); // 1185ms
        System.out.println("结果："+forkJoinPool.invoke(new MyTask1(1,10))+"》》》》 耗时："+(System.currentTimeMillis()-start)+"ms"); // 779ms

    }
}
// 求累加1+2+3+4+5+6+7+8+9+10
@Slf4j
class MyTask extends RecursiveTask<Integer> {

    private int n;

    public MyTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (n <= 1) {
            return 1;
        }
        MyTask t = new MyTask(n - 1);
        t.fork(); // 令子线程执行
        log.info("{}>>>>执行合并操作：{}+{}={}", Thread.currentThread().getName(),n, t.join(), n + t.join());
        return n + t.join(); // 等待子任务结果，并合并结果
    }
}
@Slf4j
class MyTask1 extends RecursiveTask<Integer> {
    private int start;
    private int end;
    public MyTask1(int start, int end) {
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(start==end){
            return start;
        }
        if(start+1==end){
            log.info("{}>>>>执行合并操作：{}", Thread.currentThread().getName(),start+"+"+end);
            return start+end;
        }
        int mid = (start+end)>>>1;
        MyTask1 t1 = new MyTask1(start, mid);
        t1.fork();
        MyTask1 t2 = new MyTask1(mid+1, end);
        t2.fork();
        log.info("{}>>>>执行合并操作：{}", Thread.currentThread().getName(),t1.join()+"+"+t2.join());
        return t1.join()+t2.join();
    }
}

package com.liang.juc.lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {
    // 独占锁&不可重入
    class MySync extends AbstractQueuedSynchronizer{
        @Override // 尝试获取锁
        protected boolean tryAcquire(int arg) {
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override // 尝试释放锁
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override // 是否占用独占锁
        protected boolean isHeldExclusively() {
            return getState()==1;
        }
        public ConditionObject newCondition(){
            return new ConditionObject();
        }
    }
    private MySync sync = new MySync();
    @Override // 获取锁
    public void lock() {
        sync.acquire(1);
    }

    @Override // 获取锁，可中断
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override // 尝试获取锁
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override // 尝试获取锁，可中断
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override // 释放锁
    public void unlock() {
        sync.release(1);
    }

    @Override // 创建条件变量
    public Condition newCondition() {
        return sync.newCondition();
    }
}

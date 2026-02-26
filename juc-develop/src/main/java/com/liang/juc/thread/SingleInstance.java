package com.liang.juc.thread;

public final class SingleInstance {
    private static SingleInstance instance;
    public SingleInstance() {
        System.out.println("实例化，理论上说只有一次SingleInstance");
    }
    public static SingleInstance getInstance() {
        if (instance == null) {
            synchronized (SingleInstance.class) {
                if (instance == null) {
                    instance = new SingleInstance();
                }
                return instance;
            }
        }
        return instance;
    }
}

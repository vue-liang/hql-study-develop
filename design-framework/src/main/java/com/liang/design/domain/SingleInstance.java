package com.liang.design.domain;

public final class SingleInstance {
    private static volatile SingleInstance instance;
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

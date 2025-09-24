package com.liang.design.domain;

public enum SingleInstance2 {
    INSTANCE;
    SingleInstance2(){
        System.out.println("实例化，理论上说只有一次SingleInstance2");
    }
    public static SingleInstance2 getInstance(){
        return INSTANCE;
    }
}

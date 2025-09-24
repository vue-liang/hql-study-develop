package com.liang.design.domain;

public class SingleInstance1 {
    private static SingleInstance1 singleInstance1 = new SingleInstance1();

    private SingleInstance1() {
        System.out.println("实例化，理论上说只有一次SingleInstance1");
    }

    public static SingleInstance1 getInstance() {
        return singleInstance1;
    }
}

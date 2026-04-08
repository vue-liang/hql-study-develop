package com.liang.design.domain;

/**
 * @author liang
 * @create 2026-03-30 20:22
 * @description 对象创建执行流程
 */
public class ObjCreateProcess extends ObjCreateProcessFather {

    private final String filed;

    static {
        System.out.println("1...静态代码块");
    }

    {
        System.out.println("3...非静态代码块");
        this.filed = "liang";
    }

    public ObjCreateProcess() {
        System.out.println("4...执行构造方法,默认属性值：" + this.filed);
    }

}

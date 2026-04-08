package com.liang.design.test;

import com.liang.design.domain.ObjCreateProcess;
import org.junit.jupiter.api.Test;

/**
 * @author liang
 * @create 2026-03-30 20:27
 * @description Java基础Api
 */
public class ApiTest {

    /**
     * 1...静态代码块
     * 2...执行父类构造方法
     * 3...非静态代码块
     * 4...执行构造方法,默认属性值：liang
     */
    @Test
    public void test_createObj(){
        ObjCreateProcess objCreateProcess = new ObjCreateProcess();
        System.out.println(objCreateProcess);
    }

}

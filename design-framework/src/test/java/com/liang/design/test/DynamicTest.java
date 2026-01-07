package com.liang.design.test;

import com.liang.design.dynamic.CglibProxyFactory;
import com.liang.design.dynamic.JdkProxyFactory;
import com.liang.design.dynamic.TestService;
import com.liang.design.dynamic.TestServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class DynamicTest {
    @Test
    void test01(){
//        InvocationHandler invocationHandler = ((proxy, method, args) -> {
//            System.out.println("方法【"+method.getName()+"】调用之前");
//            Object res = method.invoke(proxy, args);
//            System.out.println("方法【"+method.getName()+"】调用之后");
//            return res;
//        });
        List<Integer> userIdList = new ArrayList<>() {{
            add(1);
            add(3);
            add(6);
            add(7);
            add(2);
            add(5);
            add(4);
        }};
        Collections.sort(userIdList);
        System.out.println(userIdList);
//        TestService proxy = (TestService) JdkProxyFactory.getProxy(new TestServiceImpl());
//        proxy.test();
    }
    @Test
    void test02(){
        TestService proxy = (TestService) CglibProxyFactory.getProxy(TestServiceImpl.class);
        proxy.test();
    }
}

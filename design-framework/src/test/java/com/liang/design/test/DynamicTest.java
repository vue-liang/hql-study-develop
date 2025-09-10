package com.liang.design.test;

import com.liang.design.dynamic.JdkProxyFactory;
import com.liang.design.dynamic.TestService;
import com.liang.design.dynamic.TestServiceImpl;
import org.aopalliance.intercept.Invocation;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

class DynamicTest {
    @Test
    void test01(){
//        InvocationHandler invocationHandler = ((proxy, method, args) -> {
//            System.out.println("方法【"+method.getName()+"】调用之前");
//            Object res = method.invoke(proxy, args);
//            System.out.println("方法【"+method.getName()+"】调用之后");
//            return res;
//        });
        TestService proxy = (TestService) JdkProxyFactory.getProxy(new TestServiceImpl());
        proxy.test();
    }
}

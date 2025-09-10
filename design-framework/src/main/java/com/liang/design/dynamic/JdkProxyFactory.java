package com.liang.design.dynamic;

import java.lang.reflect.Proxy;

public class JdkProxyFactory {
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("方法【"+method.getName()+"】调用之前");
                    Object res = method.invoke(target, args);
                    System.out.println("方法【"+method.getName()+"】调用之后");
                    return res;
                }
        );
    }
}

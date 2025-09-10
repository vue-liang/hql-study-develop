package com.liang.design.tree;

/**
 *  T 入参
 *  D 上下文
 *  R 返回值
 *  映射器
 */
public interface StrategyMapper<T,D,R> {

    StrategyHandler<T,D,R> get(T t,D d);
}

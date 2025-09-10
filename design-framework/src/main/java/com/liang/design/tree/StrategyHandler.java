package com.liang.design.tree;

/**
 *  T 入参
 *  D 上下文
 *  R 返回值
 *  处理器
 */
public interface StrategyHandler<T,D,R> {

    StrategyHandler DEFAULT = (T, D) -> null;

    R apply(T t, D d);
}

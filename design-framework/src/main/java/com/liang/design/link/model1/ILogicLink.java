package com.liang.design.link.model1;

/**
 * @author liang
 * @create 2025-12-14 19:33
 * @description 责任链节点
 */
public interface ILogicLink<T, D, R> extends ILogicLinkArmory<T, D, R> {

    R apply(T requestParameter, D dynamicContext);

}

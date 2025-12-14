package com.liang.design.link.model1;

/**
 * @author liang
 * @create 2025-12-14 19:33
 * @description 责任链装配
 */
public interface ILogicLinkArmory<T, D, R> {

    ILogicLink<T, D, R> next();

    void appendNext(ILogicLink<T, D, R> logicLink);

}

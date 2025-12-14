package com.liang.design.link.model1;

import com.liang.design.domain.RequestParameterTest;
import com.liang.design.domain.ResultTest;
import com.liang.design.link.model1.factory.LogicLinkFactory;

/**
 * @author liang
 * @create 2025-12-14 19:40
 * @description
 */
public abstract class AbstractLogicLink implements ILogicLink<RequestParameterTest, LogicLinkFactory.DynamicContext, ResultTest> {

    ILogicLink<RequestParameterTest, LogicLinkFactory.DynamicContext, ResultTest> next;

    protected ResultTest next(RequestParameterTest requestParameter, LogicLinkFactory.DynamicContext dynamicContext) {
        if (next == null) return null;
        return next.apply(requestParameter, dynamicContext);
    }

    @Override
    public ILogicLink<RequestParameterTest, LogicLinkFactory.DynamicContext, ResultTest> next() {
        return next;
    }

    @Override
    public void appendNext(ILogicLink<RequestParameterTest, LogicLinkFactory.DynamicContext, ResultTest> logicLink) {
        this.next = logicLink;
    }
}

package com.liang.design.link.model2.node;

import com.liang.design.domain.RequestParameterTest;
import com.liang.design.domain.ResultTest;
import com.liang.design.link.model2.ILogicHandler;
import com.liang.design.link.model2.factory.LinkNodeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LinkFirstNode implements ILogicHandler<RequestParameterTest, LinkNodeFactory.DynamicContextTest, ResultTest>{
    @Override
    public ResultTest apply(RequestParameterTest requestParameter, LinkNodeFactory.DynamicContextTest dynamicContext) throws Exception {
        log.info("第一个节点执行，不做任何处理");
        return next(requestParameter, dynamicContext);
    }
}

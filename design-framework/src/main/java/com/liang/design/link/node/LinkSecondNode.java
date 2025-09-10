package com.liang.design.link.node;

import com.liang.design.domain.RequestParameterTest;
import com.liang.design.domain.ResultTest;
import com.liang.design.link.ILogicHandler;
import com.liang.design.link.factory.LinkNodeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LinkSecondNode implements ILogicHandler<RequestParameterTest, LinkNodeFactory.DynamicContextTest, ResultTest>{
    @Override
    public ResultTest apply(RequestParameterTest requestParameter, LinkNodeFactory.DynamicContextTest dynamicContext) throws Exception {
        log.info("第二个节点执行，设置上下文id【{}】",requestParameter.getRequestParameterId());
        dynamicContext.setId(requestParameter.getRequestParameterId());
        return next(requestParameter, dynamicContext);
    }
}

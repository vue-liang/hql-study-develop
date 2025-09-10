package com.liang.design.link.node;

import com.liang.design.domain.RequestParameterTest;
import com.liang.design.domain.ResultTest;
import com.liang.design.link.ILogicHandler;
import com.liang.design.link.factory.LinkNodeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LinkThirdNode implements ILogicHandler<RequestParameterTest, LinkNodeFactory.DynamicContextTest, ResultTest>{
    @Override
    public ResultTest apply(RequestParameterTest requestParameter, LinkNodeFactory.DynamicContextTest dynamicContext) throws Exception {
        log.info("第三个节点执行,设置上下文消息【{}】",requestParameter.getRequestParameterName()+requestParameter.getRequestParameterValue());
        dynamicContext.setMessage(requestParameter.getRequestParameterName() +":"+ requestParameter.getRequestParameterValue());
        return next(requestParameter, dynamicContext);
//        return ResultTest.builder()
//                .result(dynamicContext.toString())
//                .build();
    }
}

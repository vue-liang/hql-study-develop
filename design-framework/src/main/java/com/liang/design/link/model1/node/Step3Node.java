package com.liang.design.link.model1.node;

import com.alibaba.fastjson2.JSON;
import com.liang.design.domain.RequestParameterTest;
import com.liang.design.domain.ResultTest;
import com.liang.design.link.model1.AbstractLogicLink;
import com.liang.design.link.model1.factory.LogicLinkFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author liang
 * @create 2025-12-14 19:46
 * @description
 */
@Slf4j
@Service
public class Step3Node extends AbstractLogicLink {
    @Override
    public ResultTest apply(RequestParameterTest requestParameter, LogicLinkFactory.DynamicContext dynamicContext) {
        dynamicContext.setMessage(dynamicContext.getMessage() + requestParameter.getRequestParameterValue());
        log.info("Step3Node 执行 requestParameter：{} dynamicContext：{}", requestParameter, dynamicContext);
        String result = JSON.toJSONString(dynamicContext);
        return ResultTest.builder()
                .result(result)
                .build();
    }
}

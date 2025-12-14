package com.liang.design.link.model1.factory;

import com.liang.design.domain.RequestParameterTest;
import com.liang.design.domain.ResultTest;
import com.liang.design.link.model1.ILogicLink;
import com.liang.design.link.model1.node.Step1Node;
import com.liang.design.link.model1.node.Step2Node;
import com.liang.design.link.model1.node.Step3Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author liang
 * @create 2025-12-14 19:41
 * @description
 */
@Service
@AllArgsConstructor
public class LogicLinkFactory {
    @Bean("armoryLink")
    public ILogicLink<RequestParameterTest, LogicLinkFactory.DynamicContext, ResultTest> getLogicLink(
            Step1Node step1Node,
            Step2Node step2Node,
            Step3Node step3Node
    ) {
        return armory(step1Node, step2Node, step3Node);
    }

    @SafeVarargs
    private ILogicLink<RequestParameterTest, LogicLinkFactory.DynamicContext, ResultTest> armory(ILogicLink<RequestParameterTest, LogicLinkFactory.DynamicContext, ResultTest>... nodes) {
        if (nodes == null || nodes.length == 0) return null;
        ILogicLink<RequestParameterTest, DynamicContext, ResultTest> logicLink = nodes[0];
        ILogicLink<RequestParameterTest, DynamicContext, ResultTest> concurrent = logicLink;
        for (int i = 1; i < nodes.length; i++) {
            concurrent.appendNext(nodes[i]);
            concurrent = concurrent.next();
        }
        return logicLink;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DynamicContext {
        private String id;
        private String message;
    }

}

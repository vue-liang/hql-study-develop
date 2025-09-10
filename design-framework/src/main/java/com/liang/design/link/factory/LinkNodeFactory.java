package com.liang.design.link.factory;

import com.liang.design.domain.RequestParameterTest;
import com.liang.design.domain.ResultTest;
import com.liang.design.link.BusinessLinkedList;
import com.liang.design.link.LinkedArmory;
import com.liang.design.link.node.LinkFirstNode;
import com.liang.design.link.node.LinkSecondNode;
import com.liang.design.link.node.LinkThirdNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class LinkNodeFactory {
    private LinkedArmory<RequestParameterTest, LinkNodeFactory.DynamicContextTest, ResultTest> linkedArmory;

    @Bean("testFilter")
    public BusinessLinkedList<RequestParameterTest, LinkNodeFactory.DynamicContextTest, ResultTest> getLogicLink(
            LinkFirstNode linkFirstNode,
            LinkSecondNode linkSecondNode,
            LinkThirdNode linkThirdNode
    ){
        linkedArmory = new LinkedArmory<>(linkFirstNode, linkSecondNode, linkThirdNode);
        return linkedArmory.getLogicLink();
    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContextTest{
        private Integer id;
        private String message;
    }
}

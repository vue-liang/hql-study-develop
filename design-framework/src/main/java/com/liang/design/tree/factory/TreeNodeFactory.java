package com.liang.design.tree.factory;

import com.liang.design.domain.RequestParameterTest;
import com.liang.design.domain.ResultTest;
import com.liang.design.tree.StrategyHandler;
import com.liang.design.tree.node.FirstNode;
import lombok.*;
import org.springframework.stereotype.Service;

@Service
public class TreeNodeFactory {

    private final FirstNode firstNode;

    public TreeNodeFactory(FirstNode firstNode) {
        this.firstNode = firstNode;
    }

    public StrategyHandler<RequestParameterTest, TreeNodeFactory.DynamicContextTest, ResultTest> strategyHandler(){
        return firstNode;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContextTest{
        private Integer id;
        private String message1;
        private String message2;
        private String message3;
    }
}

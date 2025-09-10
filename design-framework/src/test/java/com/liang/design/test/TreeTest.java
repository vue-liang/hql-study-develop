package com.liang.design.test;

import com.liang.design.domain.RequestParameterTest;
import com.liang.design.domain.ResultTest;
import com.liang.design.tree.StrategyHandler;
import com.liang.design.tree.factory.TreeNodeFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class TreeTest {

    @Resource
    private TreeNodeFactory treeNodeFactory;

    @Test
    void test(){
        StrategyHandler<RequestParameterTest, TreeNodeFactory.DynamicContextTest, ResultTest> strategyHandler = treeNodeFactory.strategyHandler();
        ResultTest apply = strategyHandler.apply(
                RequestParameterTest.builder()
                        .requestParameterId(1)
                        .requestParameterName("testName3")
                        .requestParameterValue("testValue2")
                        .build(),
                TreeNodeFactory.DynamicContextTest.builder()
                        .id(666)
                        .build()
        );
        System.out.println(apply);
    }

}

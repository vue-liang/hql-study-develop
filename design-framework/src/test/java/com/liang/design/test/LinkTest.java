package com.liang.design.test;

import com.liang.design.domain.RequestParameterTest;
import com.liang.design.domain.ResultTest;
import com.liang.design.link.model1.ILogicLink;
import com.liang.design.link.model1.factory.LogicLinkFactory;
import com.liang.design.link.model2.BusinessLinkedList;
import com.liang.design.link.model2.factory.LinkNodeFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class LinkTest {
    @Resource
    private BusinessLinkedList<RequestParameterTest, LinkNodeFactory.DynamicContextTest, ResultTest> testFilter;

    @Resource
    private ILogicLink<RequestParameterTest, LogicLinkFactory.DynamicContext, ResultTest> armoryLink;

    @Test
    void test1() throws Exception {
        ResultTest apply = testFilter.apply(
                RequestParameterTest.builder()
                        .requestParameterId(98)
                        .requestParameterName("liang")
                        .requestParameterValue("@*@")
                        .build(),
                LinkNodeFactory.DynamicContextTest.builder().build()
        );
        System.out.println(apply);
    }

    @Test
    void test2() throws Exception {
        ResultTest apply = armoryLink.apply(
                RequestParameterTest.builder()
                        .requestParameterId(1104)
                        .requestParameterName("2330983050")
                        .requestParameterValue("@qq.com")
                        .build(),
                LogicLinkFactory.DynamicContext.builder().build()
        );
        System.out.println(apply);
    }

}

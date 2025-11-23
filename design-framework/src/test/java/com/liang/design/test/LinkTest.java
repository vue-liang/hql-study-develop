package com.liang.design.test;

import com.liang.design.domain.RequestParameterTest;
import com.liang.design.domain.ResultTest;
import com.liang.design.link.BusinessLinkedList;
import com.liang.design.link.factory.LinkNodeFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class LinkTest {
    @Resource
    private BusinessLinkedList<RequestParameterTest, LinkNodeFactory.DynamicContextTest, ResultTest> testFilter;

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
        System.out.println("测试github提交");
    }

}

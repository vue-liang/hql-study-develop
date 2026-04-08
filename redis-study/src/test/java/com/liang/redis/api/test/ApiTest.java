package com.liang.redis.api.test;

import com.alibaba.fastjson2.JSON;
import com.liang.redis.domain.OrderService;
import com.liang.redis.domain.model.OrderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author liang
 * @create 2026-04-08 22:41
 * @description
 */
@SpringBootTest
public class ApiTest {

    @Resource
    private OrderService orderService;

    @Test
    void test_time_out_refund_job() throws InterruptedException {

        List<OrderEntity> orders = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            OrderEntity order = orderService.createOrder();
            orders.add(order);
        }

        System.out.println("超时关单测试："+JSON.toJSONString(orders));

        new CountDownLatch(1).await();

    }

}

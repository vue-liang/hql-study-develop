package com.liang.redis.domain;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import com.liang.redis.domain.model.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

/**
 * @author liang
 * @create 2026-04-08 22:31
 * @description
 */
@Slf4j
@Service
public class OrderService {

    @Resource
    private TimeOutOrderService timeOutOrderService;

    public OrderEntity createOrder() {

        OrderEntity orderEntity = OrderEntity.builder()
                .orderId(RandomUtil.randomNumbers(12))
                .totalAmount(new BigDecimal("108.99"))
                .tradeOrderStatusEnumVO(OrderEntity.TradeOrderStatusEnumVO.CREATE)
                .build();

        timeOutOrderService.setOrderToZSet(orderEntity, 1);

        log.info("创建订单成功：{}", JSON.toJSONString(orderEntity));

        return orderEntity;
    }

}

package com.liang.redis.domain;

import com.alibaba.fastjson2.JSON;
import com.liang.redis.domain.model.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.protocol.ScoredEntry;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liang
 * @create 2026-04-08 21:41
 * @description
 */
@Slf4j
@Service
public class TimeOutOrderService {

    @Resource
    private RedissonClient redissonClient;

    private final static String key = "trade_order_queue_key";


    public void setOrderToZSet(OrderEntity orderEntity, int delayMinutes) {

        RScoredSortedSet<String> orderSet = redissonClient.getScoredSortedSet(key);

        long current = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(delayMinutes);
        boolean added = orderSet.add(current, JSON.toJSONString(orderEntity));

        if (!added) {
            log.error("订单存入队列失败,订单 {}", JSON.toJSONString(orderEntity));
        }

        log.info("存入ZSet中的订单：{} 超时时间 {}", JSON.toJSONString(orderEntity), current);

    }

    public List<OrderEntity> getTimeOutOrder() {

        RScoredSortedSet<String> orderSet = redissonClient.getScoredSortedSet(key);

        // 参数：fromScore, fromInclusive(包含), toScore, toInclusive(包含)
        Collection<ScoredEntry<String>> expiredEntries = orderSet.entryRange(0, true, System.currentTimeMillis(), true);

        if (expiredEntries == null || expiredEntries.isEmpty()) {
            log.info("没有超时订单");
            return null; // 没有超时订单
        }

        List<OrderEntity> orderEntityList = new ArrayList<>();
        // 处理超时订单
        for (ScoredEntry<String> expiredEntry : expiredEntries) {
            String orderJson = expiredEntry.getValue();
            orderSet.remove(orderJson);

            OrderEntity orderEntity = JSON.parseObject(orderJson, OrderEntity.class);
            orderEntityList.add(orderEntity);
        }

        log.info("超时订单数量：{}", orderEntityList.size());

        return orderEntityList;
    }

    public void deleteOrder(OrderEntity orderEntity) {

        RScoredSortedSet<String> orderSet = redissonClient.getScoredSortedSet(key);

        boolean removed = orderSet.remove(JSON.toJSONString(orderEntity));

        if (!removed) {
            log.error("删除订单失败,订单 {}", JSON.toJSONString(orderEntity));
        }

    }


}

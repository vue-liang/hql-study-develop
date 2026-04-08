package com.liang.redis.job;

import com.liang.redis.domain.TimeOutOrderService;
import com.liang.redis.domain.model.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liang
 * @create 2026-04-08 22:15
 * @description
 */
@Slf4j
@Component
public class TimeoutRefundJob {

    @Resource
    private TimeOutOrderService timeOutOrderService;

    @Resource
    private RedissonClient redissonClient;

    @Scheduled(cron = "0 */1 * * * ?")
    public void exec() {
        RLock lock = redissonClient.getLock("order_time_out_lock");
        try {
            boolean locked = lock.tryLock(0, 3, TimeUnit.SECONDS);

            if (!locked) {
                return;
            }

            List<OrderEntity> timeOutOrderList = timeOutOrderService.getTimeOutOrder();

            for (OrderEntity orderEntity : timeOutOrderList) {
                String orderId = orderEntity.getOrderId();
                RLock orderLock = redissonClient.getLock("order_process_lock_id:" + orderId);
                try {
                    boolean tryLock = orderLock.tryLock(0, 3, TimeUnit.SECONDS);
                    if (tryLock) {
                        // 处理行为需要查询订单状态进行二次判断，中间可能会出现订单支付完成的可能
                        log.info("处理超时订单：{} ", orderEntity.getOrderId());
                    }
                } finally {
                    if (orderLock.isLocked() && orderLock.isHeldByCurrentThread()) {
                        orderLock.unlock();
                    }
                }
            }

        } catch (InterruptedException e) {
            log.error("定时任务，超时订单自动关单失败", e);
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

}

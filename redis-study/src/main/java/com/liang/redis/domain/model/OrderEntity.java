package com.liang.redis.domain.model;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author liang
 * @create 2026-04-08 21:43
 * @description 订单实体
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    private String orderId;

    private BigDecimal totalAmount;

    private TradeOrderStatusEnumVO tradeOrderStatusEnumVO;


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static enum TradeOrderStatusEnumVO {

        CREATE(0, "初始创建"),
        COMPLETE(1, "消费完成"),
        CLOSE(2, "用户退单"),
        ;

        private Integer code;
        private String info;

        public static TradeOrderStatusEnumVO valueOf(Integer code) {
            switch (code) {
                case 0:
                    return CREATE;
                case 1:
                    return COMPLETE;
                case 2:
                    return CLOSE;
            }
            return CREATE;
        }
    }

}

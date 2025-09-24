package com.liang.design.test;


import com.liang.design.strategy.GroupBuyOrderEnumVO;
import com.liang.design.strategy.IRefundService;
import com.liang.design.strategy.RefundTypeEnumVO;
import com.liang.design.strategy.TradeOrderStatusEnumVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;

@SpringBootTest
public class StrategyTest {

    @Resource
    private Map<String,IRefundService> refundServiceMap;

    @Test
    public void test() throws Exception {
        RefundTypeEnumVO refundStrategy = RefundTypeEnumVO.getRefundStrategy(GroupBuyOrderEnumVO.PROGRESS, TradeOrderStatusEnumVO.CREATE);
        IRefundService refundService = refundServiceMap.get(refundStrategy.getStrategy());
        refundService.refundOrder();
        refundService.reverseStock();
    }
}

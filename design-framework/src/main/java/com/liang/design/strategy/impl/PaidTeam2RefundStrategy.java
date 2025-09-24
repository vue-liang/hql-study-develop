package com.liang.design.strategy.impl;

import org.springframework.stereotype.Service;

@Service("paidTeam2RefundStrategy")
public class PaidTeam2RefundStrategy extends AbstractRefundStrategy{

    @Override
    public void refundOrder() throws Exception {
        sendRefundMessage("");
    }

    @Override
    public void reverseStock() throws Exception {
        doReverseStock();
    }
}

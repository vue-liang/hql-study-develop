package com.liang.design.strategy.impl;

import org.springframework.stereotype.Service;

@Service("unpaid2RefundStrategy")
public class Unpaid2RefundStrategy extends AbstractRefundStrategy{

    @Override
    public void refundOrder() throws Exception {
        sendRefundMessage("unpaid2RefundStrategy");
    }

    @Override
    public void reverseStock() throws Exception {
        doReverseStock();
    }
}

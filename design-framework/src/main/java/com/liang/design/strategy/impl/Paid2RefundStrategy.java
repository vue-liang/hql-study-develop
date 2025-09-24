package com.liang.design.strategy.impl;

import org.springframework.stereotype.Service;

@Service("paid2RefundStrategy")
public class Paid2RefundStrategy extends AbstractRefundStrategy{

    @Override
    public void refundOrder() throws Exception {
        sendRefundMessage("");
    }

    @Override
    public void reverseStock() throws Exception {
        doReverseStock();
    }
}

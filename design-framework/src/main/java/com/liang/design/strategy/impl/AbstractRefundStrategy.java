package com.liang.design.strategy.impl;

import com.liang.design.strategy.IRefundService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractRefundStrategy implements IRefundService {

    protected void sendRefundMessage(String message) {
        log.info("send refund message: {}", message);
    }
    protected void doReverseStock(){
        log.info("do reverse stock");
    }

}

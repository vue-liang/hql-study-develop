package com.liang.design.strategy;

public interface IRefundService {
    void refundOrder() throws Exception;
    void reverseStock() throws Exception;
}

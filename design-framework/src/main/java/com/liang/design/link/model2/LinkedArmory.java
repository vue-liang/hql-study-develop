package com.liang.design.link.model2;

import java.util.Arrays;

public class LinkedArmory<T,D,R> {
    private BusinessLinkedList<T,D,R> logicLink =new BusinessLinkedList<>();
    public LinkedArmory(ILogicHandler<T,D,R> ...logicHandler) {
        logicLink.addAll(Arrays.asList(logicHandler));
    }
    public BusinessLinkedList<T,D,R> getLogicLink() {
        return logicLink;
    }
}

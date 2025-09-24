package com.liang.test;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Date date = new Date(2025, 9, 24);
        for (int i = 0; i < 10; i++) {
            CompletableFuture.runAsync(()->
                System.out.println(solution.generateOrderNumber(date, "u217jf"))
            ).join();
        }
        String s = solution.generateOrderNumber(date, "u217jf");
        System.out.println(s);
//        Order order = solution.getOrder(s);
//        System.out.println(order.getCreateDate());
//        System.out.println(order.getOrderSerial());
//        System.out.println(order.getUserId());
    }

    public Solution() {
    }

    AtomicInteger count = new AtomicInteger(1);
    Date oldDate ;

    public String generateOrderNumber(Date date, String userId) {
        if(oldDate==null) {
            oldDate=date;
        }
        if(!oldDate.equals(date)) {
            count=new AtomicInteger(1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(date.getYear());
        sb.append(date.getMonth() < 10 ? "0" + date.getMonth() : date.getMonth());
        sb.append(date.getDate() < 10 ? "0" + date.getDate() : date.getDate());
        int i = count.getAndIncrement();
        int length = Integer.toString(i).length();
        if (length > 5) {
            i = i % 100000;
        }
        length = Integer.toString(i).length();
//        sb.append("0".repeat(Math.max(0, 5 - length)));
        for (int j = 0; j < 5-length; j++) {
            sb.append("0");
        }
        sb.append(i);
        sb.append(userId);
        return sb.toString();
    }

    public Order getOrder(String orderNumber) {
        if (orderNumber == null || orderNumber.length() != 21) {
            throw new IllegalArgumentException();
        }
        String year = orderNumber.substring(0, 4);
        String month = orderNumber.substring(4, 6);
        String date = orderNumber.substring(6, 8);
        Date dates = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));
        String serialNumber = orderNumber.substring(8, 13);
        String userId = orderNumber.substring(13);
        return new Order(dates, serialNumber, userId);
    }

}

class Order {
    private Date createDate;
    private String orderSerial;
    private String userId;

    public Order(Date createDate, String orderSerial, String userId) {
        this.createDate = createDate;
        this.orderSerial = orderSerial;
        this.userId = userId;
    }

    public String getOrderSerial() {
        return orderSerial;
    }

    public String getUserId() {
        return userId;
    }

    public Date getCreateDate() {
        return createDate;
    }
}

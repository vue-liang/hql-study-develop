package com.liang.juc.future;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 案例说明：电商比价需求，模拟如下情况：
 * <p>
 * 1需求：
 * 1.1 同一款产品，同时搜索出同款产品在各大电商平台的售价;
 * 1.2 同一款产品，同时搜索出本产品在同一个电商平台下，各个入驻卖家售价是多少
 * <p>
 * 2输出：出来结果希望是同款产品的在不同地方的价格清单列表，返回一个List<String>
 * 《mysql》 in jd price is 88.05
 * 《mysql》 in dangdang price is 86.11
 * 《mysql》 in taobao price is 90.43
 * <p>
 * 3 技术要求
 * 3.1 函数式编程
 * 3.2 链式编程
 * 3.3 Stream流式计算
 */
public class CompletableFutureMallDemo {

    static List<NetMall> list = Arrays.asList(
            new NetMall("拼多多"),
            new NetMall("京东"),
            new NetMall("淘宝"),
            new NetMall("美团"),
            new NetMall("天猫")
    );

    public static List<String> getPrice(List<NetMall> list, String productName) {
        return list.stream()
                .map(
                        netMall ->
                                String.format("《" + productName + "》 in %s price is %.2f",
                                        netMall.getNetMallName(),
                                        netMall.calcPrice(productName))
                ).collect(Collectors.toList());
    }

    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName) {
        return list.stream()
                .map(netMall -> CompletableFuture.supplyAsync(() ->
                        String.format("《" + productName + "》 in %s price is %.2f",
                                netMall.getNetMallName(),
                                netMall.calcPrice(productName)))
                ).collect(Collectors.toList()).stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        NetMall[] array = list.toArray(new NetMall[0]);
        System.out.println(Arrays.toString(array));

        long start = System.currentTimeMillis();
        List<String> mySQL = getPrice(list, "MySQL");
        mySQL.forEach(System.out::println);
        long end = System.currentTimeMillis();
        System.out.println("----第一次耗费时间" + (end - start) + "ms----");

        long start1 = System.currentTimeMillis();
        List<String> mySQL1 = getPriceByCompletableFuture(list, "MySQL");
        mySQL1.forEach(System.out::println);
        long end1 = System.currentTimeMillis();
        System.out.println("----第二次耗费时间" + (end1 - start1) + "ms----");

    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class NetMall {

    private String netMallName;

    public double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}

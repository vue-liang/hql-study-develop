package com.liang.juc.exam;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RpcBatchRequestTest {

    static RpcService rpcService = new RpcService();

    public static void main(String[] args) {
        // rpc 请求参数
        List<Integer> requestIds = IntStream.range(0, 1000).boxed().collect(Collectors.toList());

        // rpc 调用
        List<String> results = batchGetDetails(requestIds);

        // 输出
        for (String result : results) {
            System.out.println(result);
        }
        // 预期输出
        // details 0
        // details 1
        // details 2
        // .......
        // details 999
    }

    /**
     * 某个 rpc service 的接口只提供单个调用
     * 此处需要做一个封装，多次请求后返回
     * <p>
     * 要求按照顺序返回
     *
     * @param ids
     * @return
     */
    public static List<String> batchGetDetails(List<Integer> ids) {
        // 单次调用
        RpcService rpcService = new RpcService();
        // rpcService.rpcGetDetailsById(1);
        List<CompletableFuture<String>> futures = ids.stream()
                .map(id -> CompletableFuture.supplyAsync(()-> rpcService.rpcGetDetailsById(id)
                )).collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );
        allFutures.join();

        return futures.stream()
                .map(CompletableFuture :: join)
                .collect(Collectors.toList());
        // 在此处实现批量调用
//        return ids.stream()
//        .
//                map(id -> CompletableFuture.supplyAsync(() -> rpcService.rpcGetDetailsById(id)))
//                .collect(Collectors.toList())
//                .stream()
//                .map(CompletableFuture::join)
//                .collect(Collectors.toList());
    }
}

class RpcService {
    public String rpcGetDetailsById(int id) {
        // 模拟 rpc service 耗时
        try {
            Thread.sleep(20L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "details " + id;
    }
}

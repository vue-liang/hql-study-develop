package com.liang.design.tree.node;

import com.liang.design.domain.RequestParameterTest;
import com.liang.design.domain.ResultTest;
import com.liang.design.tree.AbstractMultiThreadStrategyRouter;
import com.liang.design.tree.StrategyHandler;
import com.liang.design.tree.factory.TreeNodeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
@Service
public class ThirdNode extends AbstractMultiThreadStrategyRouter<RequestParameterTest, TreeNodeFactory.DynamicContextTest, ResultTest> {

    @Override
    protected void multiThread(RequestParameterTest requestParameterTest, TreeNodeFactory.DynamicContextTest dynamicContextTest) {
        Callable<TreeNodeFactory.DynamicContextTest> callable = (() -> {
            dynamicContextTest.setMessage3("第三个节点,入参 name【"+requestParameterTest.getRequestParameterName()+"】");
            return dynamicContextTest;
        });
        FutureTask<TreeNodeFactory.DynamicContextTest> futureTask = new FutureTask<>(callable);
        threadPoolExecutor.execute(futureTask);
        try {
            log.info("异步执行成功，此时的上下文为：{}", futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected ResultTest doApply(RequestParameterTest requestParameterTest, TreeNodeFactory.DynamicContextTest dynamicContextTest) {
        log.info("最后处理");
        return ResultTest.builder()
                .result(dynamicContextTest.toString())
                .build();
    }

    @Override
    public StrategyHandler<RequestParameterTest, TreeNodeFactory.DynamicContextTest, ResultTest> get(RequestParameterTest requestParameterTest, TreeNodeFactory.DynamicContextTest dynamicContextTest) {
        return null;
    }
}

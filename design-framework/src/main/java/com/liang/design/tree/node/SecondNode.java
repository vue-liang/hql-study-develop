package com.liang.design.tree.node;

import com.liang.design.domain.RequestParameterTest;
import com.liang.design.domain.ResultTest;
import com.liang.design.tree.AbstractMultiThreadStrategyRouter;
import com.liang.design.tree.StrategyHandler;
import com.liang.design.tree.factory.TreeNodeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
@Service
public class SecondNode extends AbstractMultiThreadStrategyRouter<RequestParameterTest, TreeNodeFactory.DynamicContextTest, ResultTest> {

    @Resource
    private ThirdNode thirdNode;

    @Override
    protected void multiThread(RequestParameterTest requestParameterTest, TreeNodeFactory.DynamicContextTest dynamicContextTest) {
        Callable<TreeNodeFactory.DynamicContextTest> callable = (() -> {
            dynamicContextTest.setMessage2("第二个节点,入参 value【"+requestParameterTest.getRequestParameterValue()+"】");
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

        return router(requestParameterTest, dynamicContextTest);
    }

    @Override
    public StrategyHandler<RequestParameterTest, TreeNodeFactory.DynamicContextTest, ResultTest> get(RequestParameterTest requestParameterTest, TreeNodeFactory.DynamicContextTest dynamicContextTest) {
        return thirdNode;
    }
}

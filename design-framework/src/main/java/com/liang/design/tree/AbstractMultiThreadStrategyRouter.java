package com.liang.design.tree;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 继承该类的子类需要重写
 * doApply
 * multiThread
 * 并且需要设定好 get
 * 除了尾节点都需要 return router()
 * */
public abstract class AbstractMultiThreadStrategyRouter<T,D,R>
        implements StrategyMapper<T,D,R>,StrategyHandler<T,D,R>{

    @Resource
    public ThreadPoolExecutor threadPoolExecutor;

    private StrategyHandler<T,D,R> DEFAULT_STRATEGY_HANDLER = StrategyHandler.DEFAULT;

    public R router(T t,D d){
        StrategyHandler<T, D, R> strategyHandler = get(t, d);
        if(strategyHandler != null) return strategyHandler.apply(t,d);
        return DEFAULT_STRATEGY_HANDLER.apply(t,d);
    }

    @Override
    public R apply(T t, D d) {
        // 异步操作
        multiThread(t,d);
        // 返回执行结果
        return doApply(t,d);
    }

    protected abstract void multiThread(T t, D d);

    protected abstract R doApply(T t,D d);

}

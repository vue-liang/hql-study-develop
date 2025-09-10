package com.liang.redis.utils;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public interface RClient {
    void set(String key, Object value);
    void set(String key, Object value,Long ttl, TimeUnit timeUnit);
    void setWithLogicalExpire(String key,Object value, Long time , TimeUnit unit);
    <R,ID> R queryWithPassThrough(String keyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback, Long time, TimeUnit unit);
    <R,ID> R queryWithLogicalExpire( String keyPrefix,ID id,Class<R> type,Function<ID,R> dbFallback,Long time,TimeUnit unit);
}

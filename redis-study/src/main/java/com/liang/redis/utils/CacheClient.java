package com.liang.redis.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.*;
import java.util.function.Function;

@Component
@Slf4j
@RequiredArgsConstructor
public class CacheClient implements RClient{
    private final StringRedisTemplate stringRedisTemplate;


    @Override
    public void set(String key, Object value) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value));
    }

    @Override
    public void set(String key, Object value, Long ttl, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), ttl, timeUnit);
    }

    @Override
    public void setWithLogicalExpire(String key, Object value, Long time, TimeUnit unit) {
        RedisData redisData = new RedisData();
        redisData.setData(value);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData), time, unit);
    }

    @Override
    public <R, ID> R queryWithPassThrough(String keyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback, Long time, TimeUnit unit) {
        // 1.查询缓存
        String key = keyPrefix + id;
        String json = stringRedisTemplate.opsForValue().get(key);
        // 非空
        if(StrUtil.isNotBlank(json)) {
            return JSONUtil.toBean(json, type);
        }
        // 特殊空值
        if(json!=null) {
            return null;
        }
        // 2.查询数据库
        R apply = dbFallback.apply(id);
        // 未查询到数据
        if(apply == null){
            stringRedisTemplate.opsForValue().set(key,"", time, unit);
            return null;
        }
        // 3.查询到值之后设置缓存，并将其返回
        this.set(key,apply, time, unit);
        return apply;
    }
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);
    @Override
    public <R, ID> R queryWithLogicalExpire(String keyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback, Long time, TimeUnit unit) {
        // 1.查询缓存 前提是第一次需要自己设置好缓存
        SimpleRedisLock simpleRedisLock = new SimpleRedisLock("cache:"+id,stringRedisTemplate);
        String key = keyPrefix + id;
        String json = stringRedisTemplate.opsForValue().get(key);
        RedisData redisData = JSONUtil.toBean(json, RedisData.class);
        R r = JSONUtil.toBean((JSONObject) redisData.getData(), type);
        // 2.检查过期时间
        LocalDateTime expireTime = redisData.getExpireTime();
        // 已过期
        if(expireTime.isBefore(LocalDateTime.now())) {
            // 3.异步更新
            // 获取互斥锁
            Boolean lock = simpleRedisLock.tryLock(10L);
            if(!lock){
                return r;
            }
            // 异步更新
            EXECUTOR_SERVICE.submit(() -> {
                try {
                    R apply = dbFallback.apply(id);
                    this.setWithLogicalExpire(key,apply, time, unit);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    simpleRedisLock.unlock();
                }
            });
        }
        return r;
    }
}

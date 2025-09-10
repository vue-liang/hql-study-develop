package com.liang.redis.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SimpleRedisLock {
    private String name;

    public SimpleRedisLock(String name, StringRedisTemplate stringRedisTemplate) {
        this.name = name;
        this.stringRedisTemplate = stringRedisTemplate;
    }
    private StringRedisTemplate stringRedisTemplate;

    private static final String KEY_PREFIX = "lock:";
    private static final String ID_PREFIX = UUID.randomUUID() +"-";
    private static final DefaultRedisScript<Long> SCRIPT = new DefaultRedisScript<>();

    static {
        SCRIPT.setResultType(Long.class);
        SCRIPT.setLocation(new ClassPathResource("unlock.lua"));
    }
    public Boolean tryLock(Long timeout) {
        // 获取线程唯一标识 为了防止释放锁时的误删其它线程的锁的问题
        String id = ID_PREFIX+Thread.currentThread().getId();
        String key = KEY_PREFIX + name;
        Boolean b = stringRedisTemplate.opsForValue().setIfAbsent(key, id, timeout, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(b);
    }
    public void unlock() {
//        String id = ID_PREFIX+Thread.currentThread().getId();
//        String key = KEY_PREFIX + name;
//        String s = stringRedisTemplate.opsForValue().get(key);
//        // 如果这里删除锁卡顿，到达过期时间仍然会出现误删问题 非原子性问题
//        if(id.equals(s)){
//            stringRedisTemplate.delete(key);
//        }
        stringRedisTemplate.execute(
                SCRIPT,
                Collections.singletonList(KEY_PREFIX + name),
                ID_PREFIX+Thread.currentThread().getId()
        );
    }
}

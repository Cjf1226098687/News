package com.fc.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类，用来对Redis进行读取和写入操作
 */
public class RedisUtils {

    private static StringRedisTemplate template;

    /**
     * 设置键值对
     * @param key 键
     * @param value 值
     */
    public static void set(String key, String value) {
        ValueOperations<String, String> opsForValue = template.opsForValue();
        opsForValue.set(key, value, 60 * 60, TimeUnit.SECONDS);
    }

    /**
     * 通过键获取值
     *
     * @param key 键
     * @return 值
     */
    public static String get(String key) {
        ValueOperations<String, String> opsForValue = template.opsForValue();

        return opsForValue.get(key);
    }

    /**
     * 通过键删除对应的键值对
     *
     * @param keys 键
     * @return 是否删除成功
     */
    public static Boolean delete(String... keys) {
        boolean flag = false;

        Long result = template.delete(Arrays.asList(keys));

        if (0L != result) {
            flag = true;
        }

        return flag;
    }

    /**
     * 指定键自增1
     *
     * @param key 键
     * @return 自增后的值
     */
    public static Long increment(String key) {
        ValueOperations<String, String> ops = template.opsForValue();

        return ops.increment(key);
    }

    /**
     * 指定键自减1
     *
     * @param key 键
     * @return 自减后的值
     */
    public static Long decrement(String key) {
        ValueOperations<String, String> ops = template.opsForValue();

        return ops.decrement(key);
    }

    public void setTemplate(StringRedisTemplate template) {
        RedisUtils.template = template;
    }
}

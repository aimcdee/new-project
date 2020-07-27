package com.project.utils;

import com.google.gson.Gson;
import com.project.exception.RRException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author liangyuding
 * @date 2020-03-18
 */
@Component
public class RedisUtils {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ValueOperations<String, String> valueOperations;

    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;
    private final static Gson gson = new Gson();

    public void set(String key, Object value, long expire) {
        valueOperations.set(key, toJson(value));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    /**
     * 清除以key为前缀的所有ke
     */
    public String cleanKeys(String key) {
        Set<String> keys = redisTemplate.keys(key);
        for (String k : keys) {
            redisTemplate.delete(k);
        }
        return keys.toString();
    }

    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    private static final DecimalFormat decimalFormat = new DecimalFormat("000000");

    /**
     * 自增
     *
     * @param key
     * @return
     */
    public String getTodayIncdecimal(String key) {
        long l = getLong(key);
        return decimalFormat.format(l);
    }

    public long getLong(String key) {
        if (StringUtils.isBlank(key)) {
            throw new RRException("key不能为空!");
        }

        Date date = new Date(System.currentTimeMillis());
        String newKey = "ato:" + key + ":" + DateUtils.format(date, DateUtils.DATE_SIMPLE_PATTERN);
        logger.debug("getTodayIncr key:{}", newKey);
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(newKey, redisTemplate.getConnectionFactory());
        entityIdCounter.expireAt(DateUtils.getLast(date));
        long incrementAndGet = entityIdCounter.incrementAndGet();
        return incrementAndGet;
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
            object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return gson.toJson(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
package com.lsjyy.nemesis.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-09 13:21
 * @Description: redis工具类
 */
@Component
public class RedisUtil {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取value
     *
     * @param key
     * @return
     */
    public Object getValue(final String key) {
        Object value = redisTemplate.opsForValue().get(key);
        return value;
    }

    public void setValue(final String key, final Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存储list 在列表的最左边塞入一个value
     *
     * @param key
     * @param value
     */
    public void listPush(String key, List<Object> value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 模糊查询
     *
     * @param key
     * @return
     */
    public Set<String> likeGet(final String key) {
        try {
            Set<String> setKey = redisTemplate.keys(key);
            return setKey;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("###Redis 连接异常 get ===>{}", e);
            return null;
        }
    }

    /**
     * 批量获取值
     *
     * @param stringSet
     * @return
     */
    public List<Object> multiStringGet(Set<String> stringSet) {
        return redisTemplate.opsForValue().multiGet(stringSet);
    }

    /**
     * 删除key值
     *
     * @param key
     */
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 期限
     *
     * @param key
     * @param value
     * @param time
     * @param unit
     */
    public void setDateString(String key, String value, Long time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, time, unit);
    }
}

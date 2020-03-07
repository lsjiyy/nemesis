package com.lsjyy.nemesis.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-09 13:21
 * @Description: redis工具类
 */
@Component
@Slf4j
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    // ----------------  公共方法 ----------------

    /**
     * key是否存在
     * @param key
     * @return
     */
    public Boolean hasKey(String key){
        return redisTemplate.hasKey(key);
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
     * 设置key过期时间
     *
     * @param key
     * @param time
     * @param unit
     * @return
     */
    public boolean keyExpire(String key, Long time, TimeUnit unit) {
        return redisTemplate.expire(key, time, unit);
    }

    /**
     * 获取key过期时间
     *
     * @param key  键
     * @param unit
     * @return
     */
    public Long getKeyExpireTime(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }


    //------------------ String 类型 -----------------------

    /**
     * 存入普通key,value
     *
     * @param key
     * @param value
     */
    public void putString(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存入期限时间
     *
     * @param key
     * @param value
     * @param time
     * @param unit
     */
    public void putTimeString(String key, String value, Long time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

    /**
     * 批量添加 key-value (重复的键会覆盖)
     *
     * @param keyAndValue
     */
    public void batchPutString(Map<String, String> keyAndValue) {
        redisTemplate.opsForValue().multiSet(keyAndValue);
    }

    /**
     * 批量添加 key-value 只有在键不存在时,才添加
     * map 中只要有一个key存在,则全部不添加
     *
     * @param keyAndValue
     */
    public void batchPutStringIfAbsent(Map<String, String> keyAndValue) {
        redisTemplate.opsForValue().multiSetIfAbsent(keyAndValue);
    }

    /**
     * 对一个 key-value 的值进行加减操作,
     * 如果该 key 不存在 将创建一个key 并赋值该 number
     * 如果 key 存在,但 value 不是长整型 ,将报错
     *
     * @param key
     * @param number
     */
    public Long increment(String key, long number) {
        return redisTemplate.opsForValue().increment(key, number);
    }

    /**
     * 批量获取值
     *
     * @param keyList
     * @return
     */
    public List<Object> multiStringGet(List<String> keyList) {
        return redisTemplate.opsForValue().multiGet(keyList);
    }

    /**
     * 获取值
     *
     * @param key
     * @return
     */
    public Object getStringValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    //---------------------- set类型 -----------------

    /**
     * 将数据放入set缓存
     *
     * @param key 键
     * @return
     */
    public void putSet(String key, String value) {
        redisTemplate.opsForSet().add(key, value);
    }

    /**
     * 获取set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<Object> getSetMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 随机获取变量中指定个数的元素,值会重复
     *
     * @param key   键
     * @param count 值
     * @return
     */
    public List<Object> getRandomMembers(String key, long count) {
        return redisTemplate.opsForSet().randomMembers(key, count);
    }


    /**
     * 随机获取Set中的元素
     *
     * @param key 键
     * @return
     */
    public Object getRandomMember(String key) {
        return redisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 随机弹出set中的元素
     *
     * @param key 键
     * @return
     */
    public Object popSet(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    /**
     * 获取key的value长度
     *
     * @param key 键
     * @return
     */
    public long getSetSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }


    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 转移key的元素值到另一个key。
     *
     * @param key     键
     * @param value   元素对象
     * @param destKey 目的地键
     * @return
     */
    public boolean moveSet(String key, String value, String destKey) {
        return redisTemplate.opsForSet().move(key, value, destKey);
    }

    /**
     * 批量移除set缓存中元素
     *
     * @param key    键
     * @param values 值
     * @return
     */
    public void removeSet(String key, Object... values) {
        redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 通过给定的key求2个set变量的差值
     *
     * @param key     键
     * @param destKey 键
     * @return
     */
    public Set<Object> getSetDifference(String key, String destKey) {
        return redisTemplate.opsForSet().difference(key, destKey);
    }

    /**
     * 获取给定两个key的交集
     *
     * @param key
     * @param destKey
     * @return
     */
    public Set<Object> getSetIntersect(String key, String destKey) {
        return redisTemplate.opsForSet().intersect(key, destKey);
    }

    /**
     * 获取给定两个key的并集
     *
     * @param key
     * @param destKey
     * @return
     */
    public Set<Object> getSetUnion(String key, String destKey) {
        return redisTemplate.opsForSet().union(key, destKey);
    }


    //------------------ hash -----------------------------

    /**
     * 加入缓存
     *
     * @param key 键
     * @param map 键
     * @return
     */
    public void addHash(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 获取 key 下的 所有  hashkey 和 value
     *
     * @param key 键
     * @return
     */
    public Map<Object, Object> getHashEntries(String key) {
        return redisTemplate.opsForHash().entries(key);
    }


    /**
     * 验证指定 key 下 有没有指定的 hashkey
     *
     * @param key
     * @param hashKey
     * @return
     */
    public boolean existHashKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 获取指定key的值string
     *
     * @param key  键
     * @param key2 键
     * @return
     */
    public Object getHashString(String key, Object key2) {
        return redisTemplate.opsForHash().get(key, key2);
    }

    /**
     * 删除指定 hash 的 HashKey
     *
     * @param key
     * @param hashKeys
     * @return 删除成功的 数量
     */
    public Long deleteHashKey(String key, Object... hashKeys) {
        return redisTemplate.opsForHash().delete(key, hashKeys);
    }

    /**
     * 给指定 hash 的 hashkey 做增减操作
     *
     * @param key
     * @param hashKey
     * @param number
     * @return
     */
    public Long hashIncrement(String key, String hashKey, long number) {
        return redisTemplate.opsForHash().increment(key, hashKey, number);
    }


    /**
     * 获取 key 下的 所有 hashKey 字段
     *
     * @param key
     * @return
     */
    public Set<Object> getHashKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 获取指定 hash 下面的 键值对 数量
     *
     * @param key
     * @return
     */
    public Long getHashSize(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 从左插入
     *
     * @param key
     * @param value
     */
    public void pushList(String key, Object value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 从右边弹出
     *
     * @param key
     * @return
     */
    public Object popRight(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 获取指定位置元素 0是开头, -1是结尾
     *
     * @param key
     * @param index
     * @return
     */
    public Object getListIndex(String key, Long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 区间取值
     *
     * @param key
     * @param start 0 开始
     * @param end   -1  结尾
     * @return
     */
    public List<Object> getRangeList(String key, Long start, Long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 把最后一个参数值放到指定集合的第一个出现中间参数的前面，
     * 如果中间参数值存在的话。
     * <p>
     * 把值放到指定参数值前面
     *
     * @param key
     * @param pivot
     * @param value
     * @return
     */
    public void pivotLeftPush(String key, String pivot, String value) {
        redisTemplate.opsForList().leftPush(key, pivot, value);
    }

    /**
     * 向左边批量添加参数元素。
     *
     * @param key
     * @param values
     * @return
     */
    public void leftPushAll(String key, Object... values) {
        redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 向集合最右边添加元素。
     *
     * @param key
     * @param value
     * @return
     */
    public void rightPushIfPresent(String key, Object value) {
        redisTemplate.opsForList().rightPushIfPresent(key, value);
    }

    /**
     * 获取集合长度
     *
     * @param key
     * @return
     */
    public long getListLength(String key) {
        return redisTemplate.opsForList().size(key);
    }


    /**
     * 移除集合中左边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出。
     * 阻塞弹出
     *
     * @param key
     * @return
     */
    public void rightBlockPop(String key, long timeout, TimeUnit unit) {
        redisTemplate.opsForList().rightPop(key, timeout, unit);
    }

    /**
     * 批量获取相似key
     * 不要用,大量数据时会引起阻塞
     *
     * @param keys
     * @return
     */
    public Set<String> getKeys(String keys) {
        return redisTemplate.keys(keys);
    }

    /**
     * scan 实现
     * @param pattern	表达式
     * @param consumer	对迭代到的key进行操作
     */
    public void scanKeys(String pattern, Consumer<byte[]> consumer) {
        redisTemplate.execute((RedisConnection connection) -> {
            try (Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().count(Long.MAX_VALUE).match(pattern).build())) {
                cursor.forEachRemaining(consumer);
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 获取符合条件的key
     * @param pattern	表达式
     * @return
     */
    public List<String> getScanKeys(String pattern) {
        List<String> keys = new ArrayList<>();
        this.scanKeys(pattern, item -> {
            //符合条件的key
            String key = new String(item, StandardCharsets.UTF_8);
            keys.add(key);
        });
        return keys;
    }

    //---------------------------- zset --------------------

    /**
     * zset存储
     *
     * @param key
     * @param value
     * @param score 用来排序
     */
    public void zSetAdd(String key, Object value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 根据score获取区间值
     *
     * @param key
     * @param scoreA
     * @param scoreB
     * @return
     */
    public Set<Object> zSetRangeByScore(String key, double scoreA, double scoreB) {
        return redisTemplate.opsForZSet().rangeByScore(key, scoreA, scoreB);
    }

    /**
     * 根据score移除区间值
     *
     * @param key
     * @param scoreA
     * @param scoreB
     */
    public void removeRangeByScore(String key, double scoreA, double scoreB) {
        redisTemplate.opsForZSet().removeRangeByScore(key, scoreA, scoreB);
    }

    /**
     * 获取区间的元素个数
     *
     * @param key
     * @param scoreA
     * @param scoreB
     * @return
     */
    public Long scoreCount(String key, double scoreA, double scoreB) {
        return redisTemplate.opsForZSet().count(key, scoreA, scoreB);
    }

    /**
     * 获取变量中元素的索引,下标开始位置为0
     *
     * @param key
     * @param object
     * @return
     */
    public Long zSetRank(String key, Object object) {
        return redisTemplate.opsForZSet().rank(key, object);
    }

    public Set<Object> getZSet(String key, Long start, Long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }
}

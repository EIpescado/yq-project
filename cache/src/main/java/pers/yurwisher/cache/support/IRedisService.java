package pers.yurwisher.cache.support;

import org.springframework.data.redis.core.RedisTemplate;
import pers.yurwisher.cache.exception.CacheException;

/**
 * @author yq
 * @date 2018/12/05 11:10
 * @description redis
 * @since V1.0.0
 */
public interface IRedisService<T> {

    /**
     * 存值
     * @param hash hash
     * @param key key
     * @param value 值
     */
    default void putValue(String hash, String key, T value){
        redisTemplate().opsForHash().put(hash,key,value);
    }

    /**
     * 取值,若取不到则从数据持有方获取并存入redis
     * @param hash hash
     * @param key key
     * @return val
     */
    @SuppressWarnings("unchecked")
    default T getValue(String hash, String key){
        Object val = redisTemplate().opsForHash().get(hash,key);
        if(val != null){
            return (T) val;
        }else {
            T t ;
            try {
                t = originalData();
                if (t != null){
                    //存入redis
                    putValue(hash,key,t);
                    return t;
                }
            } catch (Throwable throwable) {
                throw new CacheException("get originalData error",throwable);
            }
        }
        return null;
    }

    /**
     * 存值
     * @param key key
     * @param value 值
     */
    default void putValue(String key, T value){
        redisTemplate().opsForValue().set(key,value);
    }

    /**
     * 取值,若取不到则从数据持有方获取并存入redis
     * @param key key
     * @return val
     */
    @SuppressWarnings("unchecked")
    default T getValue(String key){
        Object val = redisTemplate().opsForValue().get(key);
        if(val != null){
            return (T) val;
        }else {
            T t ;
            try {
                t = originalData();
                if (t != null){
                    //存入redis
                    putValue(key,t);
                    return t;
                }
            } catch (Throwable throwable) {
                throw new CacheException("get originalData error",throwable);
            }
        }
        return null;
    }


    /**
     * 得到数据,如从数据库查询
     * @return data
     * @throws Throwable
     */
    T originalData() throws Throwable;

    /**
     * redis模版
     * @return redisTemplate
     */
    RedisTemplate<String, Object> redisTemplate();
}

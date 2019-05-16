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
     * @param expireTime 过期时间
     */
    default void putValue(String hash, String key, T value,long expireTime){
        if(expireTime <= -1L){
            putValue(hash,key,value);
        }else {
            redisTemplate().opsForHash().put(hash,key,new CacheObject<>(value,System.currentTimeMillis() + expireTime));
        }
    }

    /**
     * 存值,永不过期
     * @param hash hash
     * @param key key
     * @param value 值
     */
    default void putValue(String hash, String key, T value){
        redisTemplate().opsForHash().put(hash,key,new CacheObject<>(value));
    }

    /**
     * 取值,若取不到则从数据持有方获取并存入redis
     * @param hash hash
     * @param key key
     * @param expireTime 过期时间
     * @return val
     */
    @SuppressWarnings("unchecked")
    default T getValue(String hash, String key,long expireTime){
        Object val = redisTemplate().opsForHash().get(hash,key);
        if(val != null){
            CacheObject<T> cacheObject = (CacheObject<T>) val;
            //尚未过期
            if(cacheObject.getExpiresTime() == -1L || cacheObject.getExpiresTime() > System.currentTimeMillis()){
                return cacheObject.getValue();
            }else {
                //已过期重新获取值 并存入redis
                T t = getValue();
                if(t != null){
                    //存入redis
                    putValue(hash,key,t,expireTime);
                }
                return t;
            }
        }else {
            //缓存中没有重新获取 并存入redis
            T t = getValue();
            if(t != null){
                //存入redis
                putValue(hash,key,t,expireTime);
            }
            return t;
        }
    }

    /**
     * 调用接口获取数据
     * @return t
     */
    default T getValue(){
        T t ;
        try {
            t = originalData();
            if (t != null){
                return t;
            }
        } catch (Throwable throwable) {
            throw new CacheException("get originalData error",throwable);
        }
        return null;
    }

    /**
     * 存值
     * @param key key
     * @param value 值
     */
    default void putValue(String key, T value){
        redisTemplate().opsForValue().set(key,new CacheObject<>(value));
    }

    /**
     * 存值
     * @param key key
     * @param value 值
     * @param expireTime 过期时间
     */
    default void putValue(String key, T value,long expireTime){
        if(expireTime <= -1L){
            putValue(key,value);
        }else {
            redisTemplate().opsForValue().set(key,new CacheObject<>(value,expireTime + System.currentTimeMillis()));
        }
    }

    /**
     * 取值,若取不到则从数据持有方获取并存入redis
     * @param key key
     * @return val
     */
    @SuppressWarnings("unchecked")
    default T getValue(String key,long expireTime){
        Object val = redisTemplate().opsForValue().get(key);
        if(val != null){
            CacheObject<T> cacheObject = (CacheObject<T>) val;
            //尚未过期
            if(cacheObject.getExpiresTime() == -1L || cacheObject.getExpiresTime() > System.currentTimeMillis()){
                return cacheObject.getValue();
            }else {
                //已过期重新获取值 并存入redis
                T t = getValue();
                if(t != null){
                    //存入redis
                    putValue(key,t,expireTime);
                }
                return t;
            }
        }else {
            //缓存中没有重新获取 并存入redis
            T t = getValue();
            if(t != null){
                //存入redis
                putValue(key,t,expireTime);
            }
            return t;
        }
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

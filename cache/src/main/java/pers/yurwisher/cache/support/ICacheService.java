package pers.yurwisher.cache.support;

import java.util.concurrent.TimeUnit;

/**
 * @author yq
 * @date 2019/07/18 15:01
 * @description cache service
 * @since V1.0.0
 */
public interface ICacheService {

    void put(String key, Object value);

    void put(String key, Object value, long times, TimeUnit unit);

    void put(String hash, String key, Object value);

    Object get(String key);

    Object get(String hash,String key);

    void delete(String key);

    void delete(String hash,String key);
}

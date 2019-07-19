package pers.yurwisher.cache.support;

import java.io.Serializable;

/**
 * @author yq
 * @date 2019/05/16 10:28
 * @description cache object
 * @since V1.0.0
 */
public class CacheObject implements Serializable {
    private static final long serialVersionUID = 1613976210331737945L;

    /**
     * 实际存储的值
     */
    private Object value;

    /**
     * 过期时间,-1则永不过期
     */
    private Long expiresTime;

    public CacheObject(Object value, Long expiresTime) {
        this.value = value;
        this.expiresTime = expiresTime;
    }

    public CacheObject(Object value) {
        this.value = value;
        this.expiresTime = -1L;
    }

    public CacheObject() {
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Long getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(Long expiresTime) {
        this.expiresTime = expiresTime;
    }

    /**
     * 缓存是否过期
     */
    public boolean expired(){
        return this.expiresTime != -1L && this.expiresTime < System.currentTimeMillis();
    }
}

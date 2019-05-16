package pers.yurwisher.cache.annotation;

import pers.yurwisher.cache.support.RedisType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yq
 * @date 2018/12/19 12:11
 * @description 自定义缓存注解
 * @since V1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface QCache {

    /**
     * 缓存hash
     * @return hash
     */
    String hash() default "";

    /**
     * 缓存 hash 内部 key (最高优先级)
     * @return key
     */
    String key() default "";

    /**
     * 参数表达式 (其次)
     * 例: method(A a) , 已a为key 则,使用 #a
     * @return SpringEL
     */
    String expression() default "";


    /**
     * redis数据类型
     * @return RedisType
     */
    RedisType type() default RedisType.HASH;

    /**
     * 自定义 key生成器 (最末)
     * @return key生成器
     */
    Class<?> customKeyGenerator() default Void.class;

    /**
     * 自定义 key生成器 (最末)
     * @return key生成器
     */
    Class<?> customHashGenerator() default Void.class;

    /**
     * key 过期时间,默认 -1L 永不过期
     * @return 过期时间
     */
    long keyExpiresTime() default -1L;

}

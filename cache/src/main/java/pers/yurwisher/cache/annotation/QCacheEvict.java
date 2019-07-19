package pers.yurwisher.cache.annotation;

import pers.yurwisher.cache.support.CacheType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yq
 * @date 2019/01/29 16:44
 * @description 自定义缓存清除
 * @since V1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface QCacheEvict {

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
    CacheType type() default CacheType.HASH;

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
}

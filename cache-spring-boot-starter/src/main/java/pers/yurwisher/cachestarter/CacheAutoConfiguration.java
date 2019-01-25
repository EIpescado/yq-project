package pers.yurwisher.cachestarter;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import pers.yurwisher.cache.support.QCacheAspect;
import pers.yurwisher.cache.support.QCacheSupport;

/**
 * @author yq
 * @date 2019/01/25 15:54
 * @description redis starter
 * @since V1.0.0
 */
@Configuration
@EnableConfigurationProperties(value = CacheConfig.class)
@ConditionalOnProperty(prefix = "yurwisher.cache", value = "enable", matchIfMissing = true)
public class CacheAutoConfiguration {

    @Bean
    @ConditionalOnClass(value = {RedisTemplate.class,RedisConnectionFactory.class})
    @ConditionalOnMissingBean(name = "redisTemplateForQCache")
    public RedisTemplate<String, Object> redisTemplateForQCache(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // set key serializer
        StringRedisSerializer serializer = new StringRedisSerializer();
        // 设置key序列化类，否则key前面会多了一些乱码
        template.setKeySerializer(serializer);
        template.setHashKeySerializer(serializer);

        // fastjson serializer
        GenericFastJsonRedisSerializer fastSerializer = new GenericFastJsonRedisSerializer();
        template.setValueSerializer(fastSerializer);
        template.setHashValueSerializer(fastSerializer);
        // 如果 KeySerializer 或者 ValueSerializer 没有配置，则对应的 KeySerializer、ValueSerializer 才使用这个 Serializer
        template.setDefaultSerializer(fastSerializer);

        // factory
        template.setConnectionFactory(connectionFactory);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    @ConditionalOnClass(value = {KeyGenerator.class})
    @ConditionalOnMissingBean(value = KeyGenerator.class,name = "defaultKeyGenerator")
    public KeyGenerator defaultKeyGenerator(){
        return  (target,method,params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName())
                    .append(method.getName());
            if (params != null && params.length > 0){
                for (Object obj : params) {
                    if(obj != null){
                        sb.append(obj.toString());
                    }
                }
            }
            return sb.toString();
        };
    }

    @Bean
    @ConditionalOnClass(value = {QCacheSupport.class})
    @ConditionalOnBean(name = "redisTemplateForQCache")
    public QCacheAspect qCacheAspect(RedisTemplate<String, Object> redisTemplate){
        return new QCacheAspect(redisTemplate);
    }
}

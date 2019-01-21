package pers.yurwisher.cache.support;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import pers.yurwisher.cache.annotation.QCache;
import pers.yurwisher.cache.exception.CacheException;

import java.lang.reflect.Method;

/**
 * @author yq
 * @date 2018/12/20 15:40
 * @description 注解支持
 * @since V1.0.0
 */
@Component
public class QCacheSupport {

    private static final Logger logger = LoggerFactory.getLogger(QCacheSupport.class);

    private RedisTemplate<String,Object> redisTemplate;

    public QCacheSupport(RedisTemplate<String, Object> redisTemplate) {
        logger.info("QCache 注解支持");
        this.redisTemplate = redisTemplate;
    }

    public Object support(ProceedingJoinPoint pjp) throws Throwable{
        Class<?> classTarget = pjp.getTarget().getClass();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodName = pjp.getSignature().getName();
        Method objMethod = classTarget.getMethod(methodName, signature.getParameterTypes());
        QCache qCache = objMethod.getAnnotation(QCache.class);
        QCache.RedisType redisType = qCache.type();
        Object result = null;
        switch (redisType){
            case STRING:
                result = stringHandle(qCache,pjp,signature);
                break;
            case HASH:
                result = hashHandle(qCache,pjp,signature);
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * hash 处理
     */
    private Object hashHandle(QCache qCache,ProceedingJoinPoint pjp,MethodSignature signature){
        String hash = calculateHash(qCache);
        if(isEmpty(hash)){
            throw new CacheException("hash value is empty");
        }
        String key = calculateKey(qCache, pjp, signature);
        if(isEmpty(key)){
            throw new CacheException("key value is empty");
        }
        IRedisService redisService =  getRedisService(pjp);
        return redisService.getValue(hash,key);
    }

    /**
     * String 处理
     */
    private Object stringHandle(QCache qCache,ProceedingJoinPoint pjp,MethodSignature signature){
        String key = calculateKey(qCache, pjp, signature);
        if(isEmpty(key)){
            throw new CacheException("key value is empty");
        }
        IRedisService redisService =  getRedisService(pjp);
        return redisService.getValue(key);
    }

    /**
     * 得到redis service
     */
    private IRedisService getRedisService(ProceedingJoinPoint pjp){
        return new IRedisService() {
            @Override
            public Object originalData() throws Throwable {
                return pjp.proceed();
            }

            @Override
            public RedisTemplate<String, Object> redisTemplate() {
                return redisTemplate;
            }
        };
    }

    private boolean isEmpty(String x){
        return x == null || x.isEmpty();
    }

    /**
     * 得到key值
     */
    private String calculateKey(QCache qCache,ProceedingJoinPoint pjp,MethodSignature signature){
        //key ,最高优先级
        String key = qCache.key() ;
        if(isEmpty(key)){
            //表达式 其次
            String expression = qCache.expression();
            if (!isEmpty(expression)) {
                //解析表达式
                Object dynamicValue = CustomSpringExpressionLanguageParser.getDynamicValue(signature.getParameterNames(), pjp.getArgs(),expression);
                if(dynamicValue != null){
                    return dynamicValue.toString();
                }
            }
        }else {
            return key;
        }
        if(isEmpty(key)){
            //自定义key生成器 最低
            Class<?> customKeyGeneratorClass = qCache.customKeyGenerator();
            if(customKeyGeneratorClass != Void.class){
                try{
                    CustomKeyGenerator customKeyGenerator = (CustomKeyGenerator) customKeyGeneratorClass.newInstance();
                    return customKeyGenerator.generateKey();
                }catch (Exception e){
                    throw new CacheException("create customKeyGenerator error", e);
                }
            }
        }
        return key;
    }

    /**
     * 得到HASH
     */
    private String calculateHash(QCache qCache){
        String hash = qCache.hash();
        if(isEmpty(hash)){
            //自定义hash生成器
            Class<?> customHashGeneratorClass = qCache.customHashGenerator();
            if(customHashGeneratorClass != Void.class){
                try{
                    CustomHashGenerator customHashGenerator = (CustomHashGenerator) customHashGeneratorClass.newInstance();
                    return customHashGenerator.generateHash();
                }catch (Exception e){
                    throw new CacheException("create customHashGenerator error", e);
                }
            }
        }
        return hash;
    }
}

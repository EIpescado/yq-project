package pers.yurwisher.cache.support;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import pers.yurwisher.cache.annotation.QCache;
import pers.yurwisher.cache.annotation.QCacheEvict;
import pers.yurwisher.cache.exception.CacheException;

import java.lang.reflect.Method;

/**
 * @author yq
 * @date 2018/12/20 15:40
 * @description 注解支持
 * @since V1.0.0
 */
public class QCacheSupport {

    private static final Logger logger = LoggerFactory.getLogger(QCacheSupport.class);

    private RedisTemplate<String,Object> redisTemplate;

    public QCacheSupport(RedisTemplate<String, Object> redisTemplate) {
        logger.info("QCache 注解支持");
        this.redisTemplate = redisTemplate;
    }

    public Object supportQCache(ProceedingJoinPoint pjp) throws Throwable{
        Class<?> classTarget = pjp.getTarget().getClass();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodName = pjp.getSignature().getName();
        Method objMethod = classTarget.getMethod(methodName, signature.getParameterTypes());
        QCache qCache = objMethod.getAnnotation(QCache.class);
        RedisType redisType = qCache.type();
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

    public void supportQCacheEvict(JoinPoint point) throws Throwable{
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method objMethod = signature.getMethod();
        QCacheEvict qCache = objMethod.getAnnotation(QCacheEvict.class);
        RedisType redisType = qCache.type();
        switch (redisType){
            case STRING:
                stringEvict(qCache,point,signature);
                break;
            case HASH:
                hashEvict(qCache,point,signature);
                break;
            default:
                break;
        }
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
        return redisService.getValue(hash,key,qCache.keyExpiresTime());
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
        return redisService.getValue(key,qCache.keyExpiresTime());
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
        return x == null || x.trim().isEmpty();
    }

    /**
     * 得到key值
     */
    private String calculateKey(QCache qCache,ProceedingJoinPoint pjp,MethodSignature signature){
        return calculateKey(qCache.key(),qCache.expression(),qCache.customKeyGenerator(),signature,pjp.getArgs());
    }

    private String calculateKey(QCacheEvict qCache,JoinPoint point,MethodSignature signature){
        return calculateKey(qCache.key(),qCache.expression(),qCache.customKeyGenerator(),signature,point.getArgs());
    }

    private String calculateKey(String key,String expression,Class<?> customKeyGeneratorClass,MethodSignature signature,Object[] args){
        //key ,最高优先级
        if(isEmpty(key)){
            //表达式 其次
            if (!isEmpty(expression)) {
                //解析表达式
                key = CustomSpringExpressionLanguageParser.parseKey(signature.getMethod(),args,expression);
            }
        }else {
            return key;
        }
        if(isEmpty(key)){
            //自定义key生成器 最低
            if(customKeyGeneratorClass != Void.class){
                try{
                    //todo 将此对象缓存起来
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
        return calculateHash(qCache.hash(),qCache.customHashGenerator());
    }

    private String calculateHash(QCacheEvict qCacheEvict){
        return calculateHash(qCacheEvict.hash(),qCacheEvict.customHashGenerator());
    }

    /**
     * 计算出Hash
     * @param hash hash
     * @param customHashGeneratorClass 自定义hash生成类
     * @return hash
     */
    private String  calculateHash( String hash, Class<?> customHashGeneratorClass){
        if(isEmpty(hash)){
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

    /**
     * 移除指定hash
     */
    private void hashEvict(QCacheEvict qCache,JoinPoint point,MethodSignature signature){
        String hash = calculateHash(qCache);
        if(isEmpty(hash)){
            throw new CacheException("hash value is empty");
        }
        String key = calculateKey(qCache,point,signature);
        if(isEmpty(key)){
            throw new CacheException("key value is empty");
        }
        redisTemplate.opsForHash().delete(hash,key);
    }

    /**
     * 移除指定hash
     */
    private void stringEvict(QCacheEvict qCache,JoinPoint point,MethodSignature signature){
        String key = calculateKey(qCache,point,signature);
        if(isEmpty(key)){
            throw new CacheException("key value is empty");
        }
        redisTemplate.delete(key);
    }

}

package pers.yurwisher.cache.support;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yq
 * @date 2019/01/18 15:11
 * @description 切面
 * @since V1.0.0
 */
@Aspect
@Component
public class QCacheAspect {

    private QCacheSupport qCacheSupport;

    public QCacheAspect(RedisTemplate<String, Object> redisTemplate) {
        this.qCacheSupport = new QCacheSupport(redisTemplate);
    }

    @Around("@annotation(pers.yurwisher.cache.annotation.QCache)")
    public Object cacheAround(ProceedingJoinPoint pjp) throws Throwable {
        return qCacheSupport.supportQCache(pjp);
    }

    @AfterReturning("@annotation(pers.yurwisher.cache.annotation.QCacheEvict)")
    public void qCacheEvictAround(JoinPoint point) throws Throwable {
        qCacheSupport.supportQCacheEvict(point);
    }
}

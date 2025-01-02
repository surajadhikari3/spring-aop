package io.reactivestax.cache.aspect;


import io.reactivestax.cache.Cache;
import io.reactivestax.cache.CacheEvict;
import io.reactivestax.cache.CacheManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheAspect {

    @Autowired
    private CacheManager cacheManager;

    @Pointcut("@annotation(cache)")
    public void cacheableMethods(Cache cache) {
    }

    @Pointcut("@annotation(cacheEvict)")
    public void cacheEvictionMethod(CacheEvict cacheEvict) {
    }

    @Around(value = "cacheableMethods(cache)")
    public Object cacheMethod(ProceedingJoinPoint joinPoint, Cache cache) throws Throwable {
        String cacheName = cache.cacheName();
        Object key = joinPoint.getArgs()[0];
        Object cacheValue = cacheManager.retrieveFromCache(cacheName, key);

        if (cacheValue != null) {
            return cacheValue;
        }

        Object result = joinPoint.proceed(); // call the actual method if no cache is hit
        cacheManager.addToCache(cacheName, key, result);
        return result;
    }

    @Around(value = "cacheEvictionMethod(cacheEvict)")
    public void evictCache(CacheEvict cacheEvict) {
        String cacheName = cacheEvict.cacheName();
        cacheManager.evictAll(cacheName);
    }

}

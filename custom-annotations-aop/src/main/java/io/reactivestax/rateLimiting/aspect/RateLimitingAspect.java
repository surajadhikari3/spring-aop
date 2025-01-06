package io.reactivestax.rateLimiting.aspect;

import io.reactivestax.rateLimiting.RateLimit;
import io.reactivestax.rateLimiting.RateLimiter;
import io.reactivestax.rateLimiting.exception.RateLimitExceedException;
import io.reactivestax.rateLimiting.factory.RateLimiterFactory;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class RateLimitingAspect {

    private final Map<String, RateLimiter> rateLimiters = new ConcurrentHashMap<>();

    @Around("@annotation(rateLimit)")
    @SneakyThrows
    public Object applyRateLimiting(ProceedingJoinPoint joinPoint, RateLimit rateLimit) {
        String key = joinPoint.getSignature().toString();
        rateLimiters.putIfAbsent(key, RateLimiterFactory.create(rateLimit));
        RateLimiter rateLimiter = rateLimiters.get(key);
        if(!rateLimiter.isAllowed()){
            throw new RateLimitExceedException("Rate Limit exceeded for method: " + key);
        }
        return joinPoint.proceed();
    }

}


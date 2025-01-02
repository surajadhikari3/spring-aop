package io.reactivestax.synchronization.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class SynchronizedAspect {

    private final Map<String, Object> locks = new ConcurrentHashMap();

    @Around("@annotation(synchronized)")
    public Object synchroziedExecutiion(ProceedingJoinPoint joinPoint) throws Throwable {

        String lockKey = joinPoint.getSignature().toLongString();
        Object lock = locks.computeIfAbsent(lockKey, k -> new Object());
        synchronized (lock) {
            try {
                return joinPoint.proceed();
            } finally {
                locks.remove(lockKey);
            }
        }
    }
}

package io.reactivestax.rateLimiting.retry.aop;

import io.reactivestax.rateLimiting.retry.Retry;
import io.reactivestax.rateLimiting.retry.exceptions.RetryException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RetryAspect {
    @Around("@annotation(retry)")
    public Object applyRetry(ProceedingJoinPoint joinPoint, Retry retry) throws InterruptedException {
        int attempts = 0;
        Throwable lastException = null;
        while (attempts < retry.maxRetries()) {
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                lastException = throwable;
                attempts++;
                System.err.println("Retry attempt " + attempts + "for method " + joinPoint.getSignature());
                Thread.sleep(retry.retryIntervalMillis());
            }
        }
        throw new RetryException("Method failed after retries" + joinPoint.getSignature(), lastException);
    }
}

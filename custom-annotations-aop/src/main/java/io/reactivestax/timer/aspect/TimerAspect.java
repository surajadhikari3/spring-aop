package io.reactivestax.timer.aspect;

import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import org.apache.logging.log4j.LogManager;


@Aspect
@Component
@EnableAspectJAutoProxy
public class TimerAspect {

    private static final Logger log = LogManager.getLogger(TimerAspect.class);

    @Around("@annotation(io.reactivestax.timer.Timer)")
    public Object calculateMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try{
            return joinPoint.proceed(); // call the real method
        } finally {
            long endTime = System.currentTimeMillis();
            String methodName = joinPoint.getSignature().toShortString();
            log.info("Execution time for {} : {}", methodName, endTime-startTime);
        }
    }
}

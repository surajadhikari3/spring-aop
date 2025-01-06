package io.reactivestax.rateLimiting;


import io.reactivestax.rateLimiting.enums.RateLimitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    int maxRequests();
    long windowTimeMills();
    RateLimitType algorithm() default RateLimitType.FIXED_WINDOW;
}

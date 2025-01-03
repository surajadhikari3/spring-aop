package io.reactivestax.cache.rateLimiting;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    int maxRequests();
    long windowTimeMills();
    String algorithm() default "FIXED_WINDOW";
}

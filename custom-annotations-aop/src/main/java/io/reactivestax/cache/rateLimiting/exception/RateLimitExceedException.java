package io.reactivestax.cache.rateLimiting.exception;

public class RateLimitExceedException extends RuntimeException {
    public RateLimitExceedException(String message){
        super(message);
    }
}

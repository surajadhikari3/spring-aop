package io.reactivestax.rateLimiting;

public interface RateLimiter {
    boolean isAllowed();
}

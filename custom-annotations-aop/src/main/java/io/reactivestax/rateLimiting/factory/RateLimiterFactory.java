package io.reactivestax.rateLimiting.factory;

import io.reactivestax.rateLimiting.RateLimit;
import io.reactivestax.rateLimiting.RateLimiter;
import io.reactivestax.rateLimiting.algorithms.FixedWindowHandler;
import io.reactivestax.rateLimiting.algorithms.SlidingWindowHandler;
import io.reactivestax.rateLimiting.algorithms.TokenBucketRateLimiter;

public class RateLimiterFactory {
    public static RateLimiter create(RateLimit rateLimit) {
        switch (rateLimit.algorithm()) {
            case SLIDING_WINDOW:
                return new SlidingWindowHandler(rateLimit.maxRequests(), rateLimit.windowTimeMills());
            case TOKEN_BUCKET:
                return new TokenBucketRateLimiter(rateLimit.maxRequests(), (int) rateLimit.windowTimeMills() / 1000);
            default:
                return new FixedWindowHandler(rateLimit.maxRequests(), rateLimit.windowTimeMills());
        }
    }
}

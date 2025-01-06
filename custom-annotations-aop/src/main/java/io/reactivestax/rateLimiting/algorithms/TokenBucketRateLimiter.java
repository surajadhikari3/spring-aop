package io.reactivestax.rateLimiting.algorithms;

import io.reactivestax.rateLimiting.RateLimit;
import io.reactivestax.rateLimiting.RateLimiter;
import org.springframework.stereotype.Component;

public class TokenBucketRateLimiter implements RateLimiter {
    private final int maxTokens;
    private final long refillIntervalMillis;
    private int currentToken;
    private long lastRefill;

    public TokenBucketRateLimiter(int refillIntervalMillis, int maxTokens) {
        this.maxTokens = maxTokens;
        this.refillIntervalMillis = refillIntervalMillis * 1000L / maxTokens;
        this.currentToken = maxTokens;
        this.lastRefill = System.currentTimeMillis();
    }

    @Override
    public synchronized boolean isAllowed() {
        refillTokens();
        if (currentToken > 0) {
            currentToken--;
            return true;
        }
        return false;
    }

    public void refillTokens() {
        long now = System.currentTimeMillis();
        long tokensToAdd = (now - lastRefill) / refillIntervalMillis;
        currentToken = Math.min(maxTokens, currentToken + (int) tokensToAdd);
        lastRefill += tokensToAdd * refillIntervalMillis;
    }

}

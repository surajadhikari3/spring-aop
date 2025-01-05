package io.reactivestax.rateLimiting.algorithms;

import io.reactivestax.rateLimiting.RateLimiter;

import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowHandler implements RateLimiter {
    private final AtomicInteger counter = new AtomicInteger(0);
    private long  windowStart = System.currentTimeMillis();
    private final int maxRequests;
    private  final long windowTimeMills;

    public FixedWindowHandler(int maxRequests, long windowTimeMills){
        this.maxRequests = maxRequests;
        this.windowTimeMills = windowTimeMills;
    }

    @Override
    public synchronized boolean isAllowed(){
        long currentTime = System.currentTimeMillis();
        if(currentTime - windowStart > windowTimeMills) {
            counter.set(0);
            windowStart = currentTime;
        }
        return counter.incrementAndGet() <= maxRequests;
    }
}

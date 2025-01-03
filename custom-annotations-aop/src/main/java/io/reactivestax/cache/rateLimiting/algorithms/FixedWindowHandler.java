package io.reactivestax.cache.rateLimiting.algorithms;

import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowHandler {
    private final AtomicInteger counter = new AtomicInteger(0);
    private long  windowStart = System.currentTimeMillis();
    private final int maxRequests;
    private  final long windowTimeMills;

    public FixedWindowHandler(int maxRequests, long windowTimeMills){
        this.maxRequests = maxRequests;
        this.windowTimeMills = windowTimeMills;
    }

    public synchronized boolean isAllowed(){
        long currentTime = System.currentTimeMillis();
        if(currentTime - windowStart > windowTimeMills) {
            counter.set(0);
            windowStart = currentTime;
        }
        return counter.incrementAndGet() <= maxRequests;
    }
}

package io.reactivestax.cache.rateLimiting;

import io.reactivestax.cache.rateLimiting.algorithms.FixedWindowHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {
    private final Map<String, Object> algorithmHandler = new ConcurrentHashMap<>();

    public boolean isAllowedFixedWindow(String key, int maxRequests, long windowTimeInMills){
        return  algorithmHandler.computeIfAbsent(key, k -> new FixedWindowHandler())
    }

}

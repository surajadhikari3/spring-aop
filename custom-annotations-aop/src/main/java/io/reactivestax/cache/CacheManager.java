package io.reactivestax.cache;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CacheManager {
    private Map<String,Object> cache = new ConcurrentHashMap<>();

    public Object retrieveFromCache(String cacheName, Object key){
        return cache.get(cacheName + ":" + key);
    }
    public void addToCache(String cacheName, Object key, Object value){
        cache.put(cacheName + ":" + key, value);
    }

    public void evictCache(String cacheName, Object key){
        cache.remove(cacheName + ":" + key);
    }
    public void evictAll(String cacheName){
        cache.keySet().removeIf(key-> key.startsWith(cacheName + ":"));
    }


}

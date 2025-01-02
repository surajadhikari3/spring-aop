package io.reactivestax.cache;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CacheManager {
    @Getter
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
        System.out.println("evicting..");
        cache.keySet().removeIf(key-> key.startsWith(cacheName + ":"));
    }

}

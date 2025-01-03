package io.reactivestax.cache.rateLimiting.algorithms;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowHandler {
    private final Queue<Long> requestTimeStamps = new ConcurrentLinkedQueue<>();
    private final int maxRequests;
    private final long windowTimeMills;

    public SlidingWindowHandler(int maxRequests, long windowTimeMills) {
        this.maxRequests = maxRequests;
        this.windowTimeMills = windowTimeMills;
    }

    //it first checks and clear the expired records..
    //it then checks the count if it fit in within the quota if yes it add if not it returns false..
    public synchronized boolean isAllowed(){
        long currentTime = System.currentTimeMillis();
        //it evicts the expired timeStamps in loop
        while(!requestTimeStamps.isEmpty() && (currentTime - requestTimeStamps.peek() > windowTimeMills)){
            requestTimeStamps.poll();
        }
        if(requestTimeStamps.size() < maxRequests){
            requestTimeStamps.offer(currentTime);
            return true;
        }
        return false;

    }

}

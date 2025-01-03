package io.reactivestax.synchronization.service;

import io.reactivestax.synchronization.Synchronized;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SynchronizedService {

    private static final Logger log = LogManager.getLogger(SynchronizedService.class);


    @Synchronized
    public void synchronizedSection() {
        log.info("Executing the thread safe manner: {}", Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
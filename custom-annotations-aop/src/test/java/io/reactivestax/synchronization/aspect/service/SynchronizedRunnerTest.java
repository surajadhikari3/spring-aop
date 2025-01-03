package io.reactivestax.synchronization.aspect.service;

import io.reactivestax.synchronization.service.SynchronizedService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class SynchronizedRunnerTest {

    @Autowired
    SynchronizedService synchronizedService;

    @Test
    public void testSynchronizedRunner(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> synchronizedService.synchronizedSection());
        }

        executorService.shutdown();
    }


}
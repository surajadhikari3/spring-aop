package io.reactivestax;

import io.reactivestax.cache.CacheManager;
import io.reactivestax.cache.entity.Product;
import io.reactivestax.cache.service.ProductService;
import io.reactivestax.rateLimiting.algorithms.TokenBucketRateLimiter;
import io.reactivestax.synchronization.service.SynchronizedService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class App {
    private static final Logger log = LogManager.getLogger(App.class);

    @Autowired
    private SynchronizedService synchronizedService;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        //Timer illustrations
        //EmployeeService employeeService = context.getBean(EmployeeService.class);
        //employeeService.performTask();


        //Cache and cacheEvicition illustrattions...
//        ProductService productService = context.getBean(ProductService.class);
//        CacheManager cacheManager = context.getBean(CacheManager.class);
//        log.info("size before 1 {}", cacheManager.getCache().size());
//
//        productService.getProductById(1, "wireless-key");
//        productService.getProductById(2, "wireless-mouse");
//        productService.getProductById(2, "wireless-mouse");
//
//        log.info("size before 2 {}", cacheManager.getCache().size());
//
//        productService.updateProduct(new Product(2, "wireless-mouse-2"));
//        productService.updateProduct(new Product(3, "wireless-mouse-3"));
//
//        log.info("size end {}", cacheManager.getCache().size());


        App mainRunner = context.getBean(App.class);
        mainRunner.implementTokenBasedRateLimiter();
        context.close();
    }

    public void testSynchronized() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> synchronizedService.synchronizedSection());
        }
        executorService.shutdown();
    }

    public void implementTokenBasedRateLimiter() {
        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(5, 5);

        // Simulate 10 requests
        for (int i = 0; i < 10; i++) {
            if (rateLimiter.isAllowed()) {
                System.out.println("Request " + (i + 1) + " allowed.");
            } else {
                System.out.println("Request " + (i + 1) + " rate limited.");
            }
            // Sleep for 1 second between requests to simulate time passing
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

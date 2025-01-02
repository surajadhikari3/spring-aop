package io.reactivestax;

import io.reactivestax.cache.CacheManager;
import io.reactivestax.cache.entity.Product;
import io.reactivestax.cache.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {
    private static final Logger log = LogManager.getLogger(App.class);


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        //Timer illustrations
        //EmployeeService employeeService = context.getBean(EmployeeService.class);
        //employeeService.performTask();


        //Cache and cacheEvicition illustrattions...
        ProductService productService = context.getBean(ProductService.class);
        CacheManager cacheManager = context.getBean(CacheManager.class);
        log.info("size before 1 {}", cacheManager.getCache().size());

        productService.getProductById(1, "wireless-key");
        productService.getProductById(2, "wireless-mouse");
        productService.getProductById(2, "wireless-mouse");

        log.info("size before 2 {}", cacheManager.getCache().size());

        productService.updateProduct(new Product(2, "wireless-mouse-2"));
        productService.updateProduct(new Product(3, "wireless-mouse-3"));

        log.info("size end {}", cacheManager.getCache().size());

        context.close();
    }
}

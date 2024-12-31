package io.reactivestax;

import io.reactivestax.cache.service.ProductService;
import io.reactivestax.timer.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        EmployeeService employeeService = context.getBean(EmployeeService.class);
//        employeeService.performTask();

        ProductService productService = context.getBean(ProductService.class);
        productService.getProductById(1, "wireless-key");
        productService.getProductById(2, "wireless-mouse");
        productService.getProductById(2, "wireless-mouse");

        context.close();
    }
}

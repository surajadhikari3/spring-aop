package io.reactivestax.cache.service;


import io.reactivestax.cache.Cache;
import io.reactivestax.cache.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Cache(cacheName = "productCache", ttl = 3600)
    public Product getProductById(int productId, String productName){
        System.out.println("hit the real method");
        return new Product(productId,productName);
    }

}

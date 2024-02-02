package com.example.api.core.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> createProduct(@RequestBody Product body);

    @GetMapping(
            value = "/product/{productId}",
            produces = "application/json")
    Mono<Product> getProduct(@PathVariable int productId);


    Mono<Void> deleteProduct(@PathVariable int productId);
}

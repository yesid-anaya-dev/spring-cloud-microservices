package com.example.product.service.controller;

import com.example.api.core.product.Product;
import com.example.api.core.product.ProductService;
import com.example.api.exceptions.InvalidInputException;
import com.example.api.exceptions.NotFoundException;
import com.example.product.service.mapper.ProductMapper;
import com.example.product.service.persistence.ProductEntity;
import com.example.product.service.persistence.ProductRepository;
import com.example.util.http.ServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static java.util.logging.Level.FINE;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ServiceUtil serviceUtil;
    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public Mono<Product> createProduct(Product body) {
        if (body.getProductId() < 1) {
            throw new InvalidInputException("Invalid productId: " + body.getProductId());
        }

        ProductEntity entity = mapper.apiToEntity(body);
        return repository.save(entity)
                .log(log.getName(), FINE)
                .onErrorMap(
                        DuplicateKeyException.class,
                        ex -> new InvalidInputException("Duplicate key, Product Id: " + body.getProductId()))
                .map(mapper::entityToApi);
    }

    @Override
    public Mono<Product> getProduct(int productId) {

        if (productId < 1) {
            throw new InvalidInputException("Invalid productId: " + productId);
        }

        log.info("Will get product info for id={}", productId);

        return repository.findByProductId(productId)
                .switchIfEmpty(Mono.error(new NotFoundException("No product found for productId: " + productId)))
                .log(log.getName(), FINE)
                .map(mapper::entityToApi)
                .map(this::setServiceAddress);
    }

    @Override
    public Mono<Void> deleteProduct(int productId) {
        if (productId < 1) {
            throw new InvalidInputException("Invalid productId: " + productId);
        }

        log.debug("deleteProduct: tries to delete an entity with productId: {}", productId);
        return repository.findByProductId(productId)
                .log(log.getName(), FINE)
                .map(repository::delete)
                .flatMap(e -> e);
    }

    private Product setServiceAddress(Product e) {
        e.setServiceAddress(serviceUtil.getServiceAddress());
        return e;
    }
}

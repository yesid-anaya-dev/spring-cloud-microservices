package com.example.product.service.config;

import com.example.api.core.product.Product;
import com.example.api.core.product.ProductService;
import com.example.api.event.Event;
import com.example.api.exceptions.EventProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.example")
@RequiredArgsConstructor
@Slf4j
public class MessageProcessorConfig {
    private final ProductService productService;

    @Bean
    public Consumer<Event<Integer, Product>> messageProcessor() {
        return event -> {
            log.info("Process message created at {}...", event.getEventCreatedAt());

            switch (event.getEventType()) {

                case CREATE:
                    Product product = event.getData();
                    log.info("Create product with ID: {}", product.getProductId());
                    productService.createProduct(product).block();
                    break;

                case DELETE:
                    int productId = event.getKey();
                    log.info("Delete product with ProductID: {}", productId);
                    productService.deleteProduct(productId).block();
                    break;

                default:
                    String errorMessage = "Incorrect event type: " + event.getEventType() + ", expected a CREATE or DELETE event";
                    log.warn(errorMessage);
                    throw new EventProcessingException(errorMessage);
            }

            log.info("Message processing done!");

        };
    }
}

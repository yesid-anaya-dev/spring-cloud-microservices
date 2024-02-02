package com.example.product.composite.service.config;

import com.example.product.composite.service.integrator.ProductCompositeIntegration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.CompositeReactiveHealthContributor;
import org.springframework.boot.actuate.health.ReactiveHealthContributor;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.example")
@RequiredArgsConstructor
@Slf4j
public class HealthCheckConfiguration {
    private final ProductCompositeIntegration integration;

    @Bean
    ReactiveHealthContributor coreServices() {

        final Map<String, ReactiveHealthIndicator> registry = new LinkedHashMap<>();

        registry.put("product", integration::getProductHealth);
        registry.put("recommendation", integration::getRecommendationHealth);
        registry.put("review", integration::getReviewHealth);

        return CompositeReactiveHealthContributor.fromMap(registry);
    }
}

package com.example.recommendation.service.config;

import com.example.api.core.recommendation.Recommendation;
import com.example.api.core.recommendation.RecommendationService;
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
    private final RecommendationService recommendationService;

    @Bean
    public Consumer<Event<Integer, Recommendation>> messageProcessor() {
        return event -> {

            log.info("Process message created at {}...", event.getEventCreatedAt());

            switch (event.getEventType()) {

                case CREATE:
                    Recommendation recommendation = event.getData();
                    log.info("Create recommendation with ID: {}/{}", recommendation.getProductId(), recommendation.getRecommendationId());
                    recommendationService.createRecommendation(recommendation).block();
                    break;

                case DELETE:
                    int productId = event.getKey();
                    log.info("Delete recommendations with ProductID: {}", productId);
                    recommendationService.deleteRecommendations(productId).block();
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

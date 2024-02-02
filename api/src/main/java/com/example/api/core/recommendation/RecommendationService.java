package com.example.api.core.recommendation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecommendationService {
    Mono<Recommendation> createRecommendation(@RequestBody Recommendation body);

    @GetMapping(
            value = "/recommendation",
            produces = "application/json")
    Flux<Recommendation> getRecommendations(
            @RequestParam(value = "productId", required = true) int productId);

    Mono<Void> deleteRecommendations(@RequestParam(value = "productId", required = true)  int productId);
}

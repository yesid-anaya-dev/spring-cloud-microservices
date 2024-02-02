package com.example.api.core.review;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReviewService {
    Mono<Review> createReview(@RequestBody Review body);

    @GetMapping(
            value = "/review",
            produces = "application/json")
    Flux<Review> getReviews(@RequestParam(value = "productId", required = true) int productId);

    Mono<Void> deleteReviews(@RequestParam(value = "productId", required = true)  int productId);
}

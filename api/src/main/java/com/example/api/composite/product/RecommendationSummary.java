package com.example.api.composite.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RecommendationSummary {
    private final int recommendationId;
    private final String author;
    private final int rate;
    private final String content;
}

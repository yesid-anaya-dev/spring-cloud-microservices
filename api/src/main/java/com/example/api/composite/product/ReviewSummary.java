package com.example.api.composite.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ReviewSummary {
    private final int reviewId;
    private final String author;
    private final String subject;
}

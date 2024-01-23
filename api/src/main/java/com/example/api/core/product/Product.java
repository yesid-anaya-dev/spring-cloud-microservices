package com.example.api.core.product;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Product {
    private final int productId;
    private final String name;
    private final int weight;
    private final String serviceAddress;
}

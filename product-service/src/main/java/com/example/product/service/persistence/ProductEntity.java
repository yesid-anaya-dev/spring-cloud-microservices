package com.example.product.service.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@Document(collection="products")
public class ProductEntity {
    @Id
    private String id;
    @Version
    private Integer version;

    @Indexed(unique = true)
    private int productId;
    private String name;
    private int weight;
}

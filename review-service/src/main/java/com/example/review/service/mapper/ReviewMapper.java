package com.example.review.service.mapper;

import java.util.List;

import com.example.api.core.review.Review;
import com.example.review.service.persistence.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "serviceAddress", ignore = true)
    Review entityToApi(ReviewEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    ReviewEntity apiToEntity(Review api);

    List<Review> entityListToApiList(List<ReviewEntity> entity);

    List<ReviewEntity> apiListToEntityList(List<Review> api);
}

package com.example.recommendation.service.mapper;

import java.util.List;

import com.example.api.core.recommendation.Recommendation;
import com.example.recommendation.service.persistence.RecommendationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RecommendationMapper {

    @Mapping(target = "rate", source = "entity.rating")
    @Mapping(target = "serviceAddress", ignore = true)
    Recommendation entityToApi(RecommendationEntity entity);

    @Mapping(target = "rating", source = "api.rate")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    RecommendationEntity apiToEntity(Recommendation api);

    List<Recommendation> entityListToApiList(List<RecommendationEntity> entity);

    List<RecommendationEntity> apiListToEntityList(List<Recommendation> api);
}

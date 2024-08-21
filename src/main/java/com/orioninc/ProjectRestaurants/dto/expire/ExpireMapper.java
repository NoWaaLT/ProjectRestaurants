package com.orioninc.ProjectRestaurants.dto.expire;

import com.orioninc.ProjectRestaurants.model.Expire;
import com.orioninc.ProjectRestaurants.model.Product;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
@DecoratedWith(ExpireMapperDecorator.class)
public interface ExpireMapper {

    ExpireMapper INSTANCE = Mappers.getMapper(ExpireMapper.class);

    @Mapping(source = "productId", target = "product.id")
    Expire expireResponseDtoToExpire(ExpireResponseDto expireResponseDTO);

    @Mapping(source = "product.id", target = "productId")
    ExpireResponseDto expireToExpireResponseDto(Expire expire);

    @Mapping(target = "batchQuantity", ignore = true)
    @Mapping(target = "expireDate", ignore = true)
    @Mapping(target = "removedProduct", ignore = true)
    @Mapping(target = "product", ignore = true)
    Expire expireRequestDtoToExpire(Product product, Float productBatch);

}

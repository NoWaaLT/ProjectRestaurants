package com.orioninc.ProjectRestaurants.dto.product;

import com.orioninc.ProjectRestaurants.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "expireSet", ignore = true)
    @Mapping(source = "restaurant", target = "restaurant.id")
    Product productDtoToProduct(ProductDto productDTO);

    @Mapping(source = "restaurant.id", target = "restaurant")
    ProductDto productToProductDto(Product product);

    @Mapping(target = "expireSet", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "restaurant", target = "restaurant.id")
    Product productAddDtoToProduct(ProductAddDto productAddDTO);
}

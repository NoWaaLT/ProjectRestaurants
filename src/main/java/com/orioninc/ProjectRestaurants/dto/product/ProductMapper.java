package com.orioninc.ProjectRestaurants.dto.product;

import com.orioninc.ProjectRestaurants.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "expireSet", ignore = true)
    @Mapping(source = "restaurant", target = "restaurant.id")
    @Mapping(source = "productPrice", target = "productPrice", qualifiedByName = "getRoundedPrice")
    Product productDtoToProduct(ProductDto productDTO);

    @Mapping(source = "restaurant.id", target = "restaurant")
    ProductDto productToProductDto(Product product);

    @Mapping(target = "expireSet", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "restaurant", target = "restaurant.id")
    @Mapping(source = "productPrice", target = "productPrice", qualifiedByName = "getRoundedPrice")
    Product productAddDtoToProduct(ProductAddDto productAddDTO);

    @Named("getRoundedPrice")
    default float getRoundedPrice(float price) {
    return BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP).floatValue();
    }
}

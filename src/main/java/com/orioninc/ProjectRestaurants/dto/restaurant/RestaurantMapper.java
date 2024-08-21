package com.orioninc.ProjectRestaurants.dto.restaurant;

import com.orioninc.ProjectRestaurants.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {
  RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

  RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "menuList", ignore = true)
  @Mapping(target = "orderList", ignore = true)
  @Mapping(target = "productList", ignore = true)
  Restaurant restaurantDtoToRestaurant(RestaurantDto restaurantDto);
}

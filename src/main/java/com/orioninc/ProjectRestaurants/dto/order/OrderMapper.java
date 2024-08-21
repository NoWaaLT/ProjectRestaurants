package com.orioninc.ProjectRestaurants.dto.order;

import com.orioninc.ProjectRestaurants.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

  OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

  @Mapping(source = "restaurant.restaurantName", target = "restaurantName")
  OrderResponseDto orderToOrderDto(Order order);
}

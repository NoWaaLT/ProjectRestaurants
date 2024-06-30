package com.orioninc.ProjectRestaurants.DTO.order;

import com.orioninc.ProjectRestaurants.exceptions.RestaurantNotFoundException;
import com.orioninc.ProjectRestaurants.model.Order;
import com.orioninc.ProjectRestaurants.model.Restaurant;
import com.orioninc.ProjectRestaurants.repository.RestaurantRepository;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class OrderResponseDTOMapper implements Function<Order, OrderResponseDTO> {

  @Override
  public OrderResponseDTO apply(Order order) {

    return new OrderResponseDTO(order.getOrderName(),
            order.getRestaurant().getRestaurantName());
  }
}

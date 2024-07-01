package com.orioninc.ProjectRestaurants.DTO.order;

import com.orioninc.ProjectRestaurants.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@AllArgsConstructor
@Service
public class OrderResponseDTOMapper implements Function<Order, OrderResponseDTO> {

  @Override
  public OrderResponseDTO apply(Order order) {

    return new OrderResponseDTO(order.getOrderName(),
            order.getRestaurant().getRestaurantName());
  }
}

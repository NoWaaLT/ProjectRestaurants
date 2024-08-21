package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.dto.order.OrderResponseDto;
import com.orioninc.ProjectRestaurants.model.Order;

import java.util.List;

public interface OrderService {
  List<OrderResponseDto> getAllOrders();

  List<OrderResponseDto> getAllOrdersByRestaurant(Long restaurantId);

  Order getOrderByUserUsername();
}

package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.DTO.order.OrderDTO;
import com.orioninc.ProjectRestaurants.DTO.order.OrderResponseDTO;
import com.orioninc.ProjectRestaurants.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderResponseDTO> getAllOrders();
    List<OrderResponseDTO> getAllOrdersByRestaurant(Long id);
    Optional<Order> getOrderByUser(String username);

}
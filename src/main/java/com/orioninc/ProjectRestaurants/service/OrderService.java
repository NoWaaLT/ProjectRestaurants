package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.DTO.order.OrderRequestDTO;
import com.orioninc.ProjectRestaurants.DTO.order.OrderResponseDTO;
import com.orioninc.ProjectRestaurants.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    // Resume here

    List<OrderRequestDTO> getAllOrders();
    Optional<Order> getOrderByUser(String username);

//    List<RestaurantDTO> getAllRestaurants();
//    RestaurantDTO getRestaurantById(Long id);
//    Restaurant saveRestaurant(RestaurantDTO restaurantDTO);
//    Restaurant updateRestaurant(RestaurantDTO restaurantDTO);
//    void deleteRestaurant(Long id);

}
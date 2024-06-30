package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.DTO.restaurant.RestaurantDTO;
import com.orioninc.ProjectRestaurants.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<RestaurantDTO> getAllRestaurants();
    RestaurantDTO getRestaurantById(Long id);
    Restaurant saveRestaurant(RestaurantDTO restaurantDTO);
    Restaurant updateRestaurant(RestaurantDTO restaurantDTO);
    void deleteRestaurant(Long id);
}

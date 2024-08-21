package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.dto.restaurant.RestaurantDto;
import com.orioninc.ProjectRestaurants.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<RestaurantDto> getAllRestaurants();
    RestaurantDto getRestaurantById(Long id);
    Restaurant saveRestaurant(RestaurantDto restaurantDTO);
    Restaurant updateRestaurant(RestaurantDto restaurantDTO);
    void deleteRestaurant(Long id);
}

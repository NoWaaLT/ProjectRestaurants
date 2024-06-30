package com.orioninc.ProjectRestaurants.DTO.restaurant;

import com.orioninc.ProjectRestaurants.enums.RestaurantType;

public record RestaurantDTO(String restaurantName,
                            RestaurantType restaurantType) {
}

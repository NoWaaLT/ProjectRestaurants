package com.orioninc.ProjectRestaurants.DTO.restaurant;

import com.orioninc.ProjectRestaurants.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RestaurantRequestDTOMapper implements Function<RestaurantDTO, Restaurant> {
    @Override
    public Restaurant apply(RestaurantDTO restaurantDTO) {
        return new Restaurant(
                null,
                restaurantDTO.restaurantName(),
                restaurantDTO.restaurantType(),
                null,
                null,
                null
        );
    }
}

package com.orioninc.ProjectRestaurants.DTO.restaurant;

import com.orioninc.ProjectRestaurants.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RestaurantResponseDTOMapper implements Function<Restaurant, RestaurantDTO>{
    @Override
    public RestaurantDTO apply(Restaurant restaurant) {
        return new RestaurantDTO(
                restaurant.getRestaurantName(),
                restaurant.getRestaurantType()
        );
    }
}

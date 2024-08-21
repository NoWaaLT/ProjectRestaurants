package com.orioninc.ProjectRestaurants.dto.restaurant;

import com.orioninc.ProjectRestaurants.enums.RestaurantType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RestaurantDto(
        @NotNull(message = "Restaurant name must be specified.")
        @NotBlank(message = "Restaurant name must be specified.")
        @Size(min = 2, max = 50, message = "Restaurant name length must be from 2 to 50 length.")
        String restaurantName,
        @NotNull(message = "Restaurant type must be specified.")
        @NotBlank(message = "Restaurant type must be specified.")
        RestaurantType restaurantType) {
}

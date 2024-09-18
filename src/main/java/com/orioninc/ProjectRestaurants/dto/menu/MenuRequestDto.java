package com.orioninc.ProjectRestaurants.dto.menu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record MenuRequestDto(

        @NotNull(message = "Menu name must be specified.")
        @NotBlank(message = "Menu name must be specified.")
        @Size(min = 5, max = 50, message = "Menu name length must be from 5 to 50 length.")
        String menuName,

        @NotNull(message = "Restaurant id must be specified.")
        @Positive(message = "Restaurant id must be positive number.")
        Long restaurant) {

}
//        @NotNull(message = "Restaurant name must be specified.")
//        @NotBlank(message = "Restaurant name must be specified.")
//        String restaurantName) {
package com.orioninc.ProjectRestaurants.dto.dish;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record DishDto(
    @NotNull(message = "Dish id must be specified.")
    @Positive(message = "Dish id must be a positive number.")
    Long id,
    @NotNull(message = "Dish name must be specified.")
    @NotBlank(message = "Dish name must be specified.")
    String dishName,
    @NotNull(message = "Dish price must be specified.")
    @Positive(message = "Dish price must be positive number.")
    Float dishPrice,
    @NotNull(message = "Menu id must be specified.")
    @Positive(message = "Menu id must be positive number.")
    Long menuId) {}

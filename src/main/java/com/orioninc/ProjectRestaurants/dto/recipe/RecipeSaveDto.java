package com.orioninc.ProjectRestaurants.dto.recipe;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RecipeSaveDto(
        @NotNull(message = "Dish id must be specified.")
        @Positive(message = "Dish id must be a positive number.")
        Long dishId,
        @NotNull(message = "Product id must be specified.")
        @Positive(message = "Product id must be a positive number.")
        Long productId,
        @NotNull(message = "Product quantity must be specified.")
        @Positive(message = "Product quantity must be positive number.")
        Integer quantity) {
}

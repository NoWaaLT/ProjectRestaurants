package com.orioninc.ProjectRestaurants.dto.recipe;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RecipeUpdateDto(
        @NotNull(message = "Recipe id must be specified.")
        @Positive(message = "Recipe id must be a positive number.")
        Long id,
        @NotNull(message = "Dish id must be specified.")
        @Positive(message = "Dish id must be a positive number.")
        Long dishId,
        @NotNull(message = "Product id must be specified.")
        @Positive(message = "Product id must be a positive number.")
        Long productId,
        @NotNull(message = "Product quantity must be specified.")
        @Positive(message = "Product quantity must higher than 0.")
        Integer quantity) {
}

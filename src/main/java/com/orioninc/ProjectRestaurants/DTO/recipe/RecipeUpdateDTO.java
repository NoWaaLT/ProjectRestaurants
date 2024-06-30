package com.orioninc.ProjectRestaurants.DTO.recipe;

public record RecipeUpdateDTO(Long id,
                              Long dishId,
                              Long productId,
                              Integer quantity) {
}

package com.orioninc.ProjectRestaurants.DTO.recipe;

public record RecipeSaveDTO(Long dishId,
                            Long productId,
                            Integer quantity) {
}

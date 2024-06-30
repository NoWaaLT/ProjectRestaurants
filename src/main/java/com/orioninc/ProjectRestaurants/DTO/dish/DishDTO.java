package com.orioninc.ProjectRestaurants.DTO.dish;

public record DishDTO(Long id,
                      String dishName,
                      Float dishPrice,
                      Long menuId) {
}

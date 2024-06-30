package com.orioninc.ProjectRestaurants.DTO.menu;

public record MenuRequestDTO(String menuName,
                             Long restaurant,
                             String restaurantName) {
}

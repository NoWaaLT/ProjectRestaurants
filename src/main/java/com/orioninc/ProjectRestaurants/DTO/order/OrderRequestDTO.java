package com.orioninc.ProjectRestaurants.DTO.order;

public record OrderRequestDTO(Long id,          // TODO Check do we need it later
                              String orderName,
                              Long restaurantId,
                              Long userId) {
}
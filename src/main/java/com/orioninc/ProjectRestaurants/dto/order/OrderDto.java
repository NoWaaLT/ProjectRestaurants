package com.orioninc.ProjectRestaurants.dto.order;

public record OrderDto(Long id,          // TODO Check do we need it later
                       String orderName,
                       Long restaurantId,
                       Long userId) {
}

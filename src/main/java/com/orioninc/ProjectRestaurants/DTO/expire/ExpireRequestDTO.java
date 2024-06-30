package com.orioninc.ProjectRestaurants.DTO.expire;

public record ExpireRequestDTO(Long id,
                               Integer expireDuration,
                               Float batchQuantity,
                               Long productId) {
}

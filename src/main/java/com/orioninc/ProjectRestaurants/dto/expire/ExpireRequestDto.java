package com.orioninc.ProjectRestaurants.dto.expire;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ExpireRequestDto(
        @NotNull(message = "Expire id must be specified.")
        @Positive(message = "Expire id must be a positive number.")
        Long id,
        @NotNull(message = "Expire duration must be specified.")
        @PositiveOrZero(message = "Expire duration must be positive number.")
        Integer expireDuration,
        @NotNull(message = "Batch quantity must be specified.")
        @Positive(message = "Batch quantity price must be positive number.")
        Float batchQuantity,
        @NotNull(message = "Product id must be specified.")
        @Positive(message = "Product id must be a positive number.")
        Long productId) {
}

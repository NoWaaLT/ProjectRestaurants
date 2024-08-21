package com.orioninc.ProjectRestaurants.dto.product;

import jakarta.validation.constraints.*;

public record ProductAddDto(
                            @NotNull(message = "Product name must be specified.")
                            @NotBlank(message = "Product name must be specified.")
                            @Size(min = 2, max = 50, message = "Product name length must be from 2 to 50 length.")
                            String productName,
                            @NotNull(message = "Product price must be specified.")
                            @Positive(message = "Product price must be positive number.")
                            Float productPrice,
                            @NotNull(message = "Product balance must be specified.")
                            @Positive(message = "Product balance must be positive number.")
                            Float productBalance,
                            @NotNull(message = "Restaurant id must be specified.")
                            @Positive(message = "Restaurant id must be positive number.")
                            Long restaurant,
                            @NotNull(message = "Product minimum balance must be specified.")
                            @Positive(message = "Product balance must be positive number.")
                            Float productMinimumBalance,
                            @NotNull(message = "Expiration must be specified.")
                            @PositiveOrZero(message = "Expiration days must be 0 or higher.")
                            Integer productExpiration) {
}
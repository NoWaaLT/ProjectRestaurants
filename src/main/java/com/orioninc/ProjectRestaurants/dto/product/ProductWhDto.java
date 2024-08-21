package com.orioninc.ProjectRestaurants.dto.product;

public record ProductWhDto(String productName,
                           Float productPrice,
                           Float productBalance,
                           Long restaurant,
                           Integer productExpiration) {
}

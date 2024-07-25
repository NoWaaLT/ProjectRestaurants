package com.orioninc.ProjectRestaurants.DTO.product;

public record ProductDTO(Long id,
                         String productName,
                         Float productPrice,
                         Float productBalance,
                         Long restaurant,
                         Float productMinimumBalance,
                         Integer productExpirable) {
}
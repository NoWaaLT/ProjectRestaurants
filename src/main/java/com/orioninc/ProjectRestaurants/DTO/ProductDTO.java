package com.orioninc.ProjectRestaurants.DTO;

public record ProductDTO(Long id,
                         String productName,
                         Float productPrice,
                         Integer productBalance,
                         Long restaurant) {
}

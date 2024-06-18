package com.orioninc.ProjectRestaurants.dto;

public record ProductDTO(Long id,
                         String productName,
                         Float productPrice,
                         Integer productBalance,
                         Long restaurant) {
}

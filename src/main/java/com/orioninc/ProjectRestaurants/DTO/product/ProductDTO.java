package com.orioninc.ProjectRestaurants.DTO.product;

import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.repository.ProductRepository;

public record ProductDTO(Long id,
                         String productName,
                         Float productPrice,
                         Float productBalance,
                         Long restaurant,
                         Float productMinimumBalance,
                         Integer productExpirable) {
}
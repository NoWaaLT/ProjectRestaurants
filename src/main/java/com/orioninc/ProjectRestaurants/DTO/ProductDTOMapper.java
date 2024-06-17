package com.orioninc.ProjectRestaurants.DTO;

import com.orioninc.ProjectRestaurants.model.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {
    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getProductName(),
                product.getProductPrice(),
                product.getProductBalance(),
                product.getRestaurant().getId());
    }
}

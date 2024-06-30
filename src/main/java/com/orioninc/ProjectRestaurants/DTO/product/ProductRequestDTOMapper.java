package com.orioninc.ProjectRestaurants.DTO.product;

import com.orioninc.ProjectRestaurants.exceptions.RestaurantNotFoundException;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.model.Restaurant;
import com.orioninc.ProjectRestaurants.repository.RestaurantRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class ProductRequestDTOMapper implements Function<ProductDTO, Product> {

    private final RestaurantRepository restaurantRepository;

    @Override
    public Product apply(ProductDTO productRequestDTO) {
        Product product = new Product();

        product.setId(productRequestDTO.id());
        product.setProductName(productRequestDTO.productName());
        product.setProductPrice(productRequestDTO.productPrice());


        product.setProductBalance(productRequestDTO.productBalance());

        Restaurant restaurant = restaurantRepository.findById(productRequestDTO.restaurant())
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));
        product.setRestaurant(restaurant);

        product.setProductMinimumBalance(productRequestDTO.productMinimumBalance());
        product.setProductExpirable(productRequestDTO.productExpirable());

        return product;
    }
}
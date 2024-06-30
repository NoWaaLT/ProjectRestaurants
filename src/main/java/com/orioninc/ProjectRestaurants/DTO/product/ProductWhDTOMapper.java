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
public class ProductWhDTOMapper implements Function<ProductWhDTO, Product> {

    private final RestaurantRepository restaurantRepository;

    @Override
    public Product apply(ProductWhDTO productWhDTO) {
        Product product = new Product();
        product.setId(productWhDTO.id());
        product.setProductName(productWhDTO.productName());
        product.setProductPrice(productWhDTO.productPrice());
        product.setProductBalance(productWhDTO.productBalance());

        Restaurant restaurant = restaurantRepository.findById(productWhDTO.restaurant()).orElseThrow(()
                -> new RestaurantNotFoundException("Restaurant is not found."));

        product.setRestaurant(restaurant);
        product.setProductMinimumBalance(null); // ????
        product.setProductExpirable(productWhDTO.productExpirable());

        return product;
    }
}

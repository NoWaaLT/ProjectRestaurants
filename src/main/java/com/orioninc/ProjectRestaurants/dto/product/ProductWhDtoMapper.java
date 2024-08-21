package com.orioninc.ProjectRestaurants.dto.product;

import com.orioninc.ProjectRestaurants.exceptions.ProductNotFoundException;
import com.orioninc.ProjectRestaurants.exceptions.RestaurantNotFoundException;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.model.Restaurant;
import com.orioninc.ProjectRestaurants.repository.ProductRepository;
import com.orioninc.ProjectRestaurants.repository.RestaurantRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

import static com.orioninc.ProjectRestaurants.enums.AppText.PRODUCT_BY_NAME_AND_RESTAURANT_NOT_FOUND;
import static com.orioninc.ProjectRestaurants.enums.AppText.RESTAURANT_BY_ID_NOT_FOUND;

@Service
@AllArgsConstructor
public class ProductWhDtoMapper implements Function<ProductWhDto, Product> {

  private final RestaurantRepository restaurantRepository;
  private final ProductRepository productRepository;

  @Override
  public Product apply(ProductWhDto productWhDTO) {
    Product product = new Product();
    Optional<Product> productOptional =
        productRepository.findProductByProductNameAndRestaurantId(
            productWhDTO.productName(), productWhDTO.restaurant());

    if (productOptional.isPresent()) {
      product.setId(productOptional.get().getId());
      product.setProductName(productWhDTO.productName());
      product.setProductPrice(productWhDTO.productPrice());
      product.setProductBalance(productWhDTO.productBalance());

      Restaurant restaurant =
          restaurantRepository
              .findById(productWhDTO.restaurant())
              .orElseThrow(
                  () ->
                      new RestaurantNotFoundException(
                          RESTAURANT_BY_ID_NOT_FOUND, productWhDTO.restaurant()));

      product.setRestaurant(restaurant);
      product.setProductMinimumBalance(productOptional.get().getProductMinimumBalance());
      product.setProductExpiration(productWhDTO.productExpiration());
    } else {
      throw new ProductNotFoundException(
          PRODUCT_BY_NAME_AND_RESTAURANT_NOT_FOUND,
          productWhDTO.productName(),
          productWhDTO.restaurant());
    }

    return product;
  }
}

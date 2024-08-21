package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.dto.expire.ExpireResponseDto;
import com.orioninc.ProjectRestaurants.dto.product.ProductAddDto;
import com.orioninc.ProjectRestaurants.dto.product.ProductDto;
import com.orioninc.ProjectRestaurants.model.Product;

import java.util.List;

public interface ProductService {
  List<ProductDto> getAllProductByRestaurantId(Long id);

  ProductDto getProductById(Long id);

  ProductDto saveProduct(ProductAddDto productAddDTO);

  Product updateProduct(ProductDto productDTO);

  List<ProductAddDto> saveProducts(List<ProductAddDto> productAddDtoList);

  void deleteProduct(Long id);

  Long findOldestDate(List<ExpireResponseDto> listOfExpires);
}

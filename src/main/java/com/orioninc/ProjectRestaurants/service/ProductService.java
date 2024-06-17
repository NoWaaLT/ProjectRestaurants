package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.DTO.ProductDTO;
import com.orioninc.ProjectRestaurants.model.Product;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAllProductByRestaurant(String id);
    Product saveProduct(Product product);
    Product updateProduct(ProductDTO productDTO);
    void deleteProduct(long id);
}

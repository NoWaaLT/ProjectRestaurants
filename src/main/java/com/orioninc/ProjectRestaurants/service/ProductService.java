package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProduct();
    Product saveProduct(Product product);
    Product updateProduct(long id, Product product);
    void deleteProduct(long id);
}

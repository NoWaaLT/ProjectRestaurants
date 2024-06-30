package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.DTO.product.ProductDTO;
import com.orioninc.ProjectRestaurants.DTO.product.ProductWhDTO;
import com.orioninc.ProjectRestaurants.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProductByRestaurant(Long id);
    ProductDTO getProductById(Long id);
    Product saveProduct(ProductDTO productDTO);
    Product saveProductFromWarehouse(ProductWhDTO productWhDTO);
    Product updateProduct(ProductDTO productDTO);
    List<ProductDTO> saveProducts(List<ProductDTO> productDTOList);
    void deleteProduct(Long id);
    void checkProductEXP(Product existingProduct, Product productBatch);

}

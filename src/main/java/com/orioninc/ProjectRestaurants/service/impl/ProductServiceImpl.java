package com.orioninc.ProjectRestaurants.service.impl;

import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.repository.InMemoryProductDAO;
import com.orioninc.ProjectRestaurants.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final InMemoryProductDAO inMemoryRepository;
    @Override
    public List<Product> findAllProduct() {
        return inMemoryRepository.findAllProduct();
    }

    @Override
    public Product saveProduct(Product product) {
        return inMemoryRepository.saveProduct(product);
    }

    @Override
    public Product updateProduct(long id, Product product) {
        return inMemoryRepository.updateProduct(product);
    }

    @Override
    public void deleteProduct(long id) {
        inMemoryRepository.deleteProduct(id);
    }
}

package com.orioninc.ProjectRestaurants.repository;

import com.orioninc.ProjectRestaurants.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class InMemoryProductDAO {
    private final List<Product> PRODUCTS = new ArrayList<>();

    public List<Product> findAllProduct() {
        return PRODUCTS;
    }

    public Product saveProduct(Product product) {
        PRODUCTS.add(product);
        return product;
    }

    public Product findByProduct(long id) {
        return PRODUCTS.stream()
                .filter(element -> element.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Need to add restaurant ID to identify it

    public Product updateProduct(Product product) {
        var productIndex = IntStream.range(0, PRODUCTS.size())
                .filter(index -> PRODUCTS.get(index).getId().equals((product.getId())))
                .findFirst().
                orElse(-1);
        if(productIndex > -1) {
            PRODUCTS.set(productIndex, product);
            return product;
        }
        return null;
    }

    public void deleteProduct(long id) {
        var product = findByProduct(id);
        if (product != null) {
            PRODUCTS.remove(product);
        }
    }
}

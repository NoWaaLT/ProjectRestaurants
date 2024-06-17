package com.orioninc.ProjectRestaurants.repository;

import com.orioninc.ProjectRestaurants.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

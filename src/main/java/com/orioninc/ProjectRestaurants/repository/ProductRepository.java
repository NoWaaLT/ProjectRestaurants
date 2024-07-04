package com.orioninc.ProjectRestaurants.repository;

import com.orioninc.ProjectRestaurants.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    @Query(value = "SELECT p FROM Product p WHERE p.productName = ?1 AND p.restaurant.id = ?2")
    Optional<Product> findProductByNameAndRestaurant(String productName, Long restaurantId);
}

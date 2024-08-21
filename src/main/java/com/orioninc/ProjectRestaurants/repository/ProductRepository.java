package com.orioninc.ProjectRestaurants.repository;

import com.orioninc.ProjectRestaurants.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    Optional<Product> findProductByProductNameAndRestaurantId(String productName, Long restaurantId);

    Collection<Product> findAllByRestaurantId(Long restaurantId);       // O

}

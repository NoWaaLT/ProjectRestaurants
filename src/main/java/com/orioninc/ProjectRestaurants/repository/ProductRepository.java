package com.orioninc.ProjectRestaurants.repository;

import com.orioninc.ProjectRestaurants.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, CustomRepository {

    Optional<Product> findProductByProductNameAndRestaurantId(String productName, Long restaurantId);

    Collection<Product> findAllByRestaurantId(Long restaurantId);       // O

}

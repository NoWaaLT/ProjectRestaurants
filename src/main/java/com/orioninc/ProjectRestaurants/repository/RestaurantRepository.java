package com.orioninc.ProjectRestaurants.repository;

import com.orioninc.ProjectRestaurants.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

//    @Query(value = "SELECT p FROM Restaurant p WHERE p.restaurant.id = ?1")
//    Optional<Product> findRestaurantNameById(Long restaurantId);
}

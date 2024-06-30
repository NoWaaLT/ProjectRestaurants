package com.orioninc.ProjectRestaurants.repository;

import com.orioninc.ProjectRestaurants.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}

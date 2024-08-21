package com.orioninc.ProjectRestaurants.repository;

import com.orioninc.ProjectRestaurants.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    Collection<Dish> findAllByMenuId(Long menuId);
//    boolean existsByMenuId(Long menId);
}

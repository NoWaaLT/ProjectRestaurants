package com.orioninc.ProjectRestaurants.repository;

import com.orioninc.ProjectRestaurants.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
//    Menu findMenuByDishId(Long dishId);

    Collection<Menu> findAllMenuByRestaurantId(Long restaurantId);

    boolean existsById(Long id);

}
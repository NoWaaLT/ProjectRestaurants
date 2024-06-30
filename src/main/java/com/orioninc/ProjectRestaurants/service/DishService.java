package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.DTO.dish.DishDTO;
import com.orioninc.ProjectRestaurants.model.Dish;

import java.util.List;

public interface DishService {
    List<DishDTO> getAllDishes();

    List<DishDTO> getAllDishesByMenuId(Long id);

    Dish getDishById(Long id);

    Dish saveDish(DishDTO dishDTO);

    Dish updateDish(DishDTO dishDTO);

    void deleteDish(Long id);

}

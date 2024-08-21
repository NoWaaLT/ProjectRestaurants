package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.dto.dish.DishDto;
import com.orioninc.ProjectRestaurants.model.Dish;

import java.util.List;

public interface DishService {
    List<DishDto> getAllDishes();

    List<DishDto> getAllDishesByMenuId(Long id);

    DishDto getDishById(Long id);

    DishDto saveDish(DishDto dishDTO);

    Dish updateDish(DishDto dishDTO);

    void deleteDish(Long id);

}

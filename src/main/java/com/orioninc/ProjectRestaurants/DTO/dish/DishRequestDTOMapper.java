package com.orioninc.ProjectRestaurants.DTO.dish;

import com.orioninc.ProjectRestaurants.exceptions.MenuNotFoundException;
import com.orioninc.ProjectRestaurants.model.Dish;
import com.orioninc.ProjectRestaurants.model.Menu;
import com.orioninc.ProjectRestaurants.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@AllArgsConstructor
@Component
public class DishRequestDTOMapper implements Function<DishDTO, Dish> {

    private final MenuRepository menuRepository;

    @Override
    public Dish apply(DishDTO dishDTO) {
        Dish dish = new Dish();

        dish.setId(dishDTO.id());
        dish.setDishName(dishDTO.dishName());
        dish.setDishPrice(dishDTO.dishPrice());

        Menu menu = menuRepository.findById(dishDTO.menuId()).orElseThrow(() ->
                new MenuNotFoundException("Menu is not found."));
        dish.setMenu(menu);

        return dish;
    }
}

package com.orioninc.ProjectRestaurants.DTO.dish;

import com.orioninc.ProjectRestaurants.model.Dish;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DishResponseDTOMapper implements Function<Dish, DishDTO> {

    @Override
    public DishDTO apply(Dish dish) {

        return new DishDTO(
                dish.getId(),
                dish.getDishName(),
                dish.getDishPrice(),
                dish.getMenu().getId()
        );
    }
}

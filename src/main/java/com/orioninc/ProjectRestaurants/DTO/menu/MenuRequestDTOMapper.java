package com.orioninc.ProjectRestaurants.DTO.menu;

import com.orioninc.ProjectRestaurants.exceptions.RestaurantNotFoundException;
import com.orioninc.ProjectRestaurants.model.Menu;
import com.orioninc.ProjectRestaurants.model.Restaurant;
import com.orioninc.ProjectRestaurants.repository.RestaurantRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class MenuRequestDTOMapper implements Function<MenuRequestDTO, Menu> {

    private final RestaurantRepository restaurantRepository;

    @Override
    public Menu apply(MenuRequestDTO menuRequestDTO) {
        Menu menu = new Menu();
        menu.setMenuName(menuRequestDTO.menuName());
        menu.setRestaurantName(menuRequestDTO.restaurantName());

        Restaurant restaurant = restaurantRepository.findById(menuRequestDTO.restaurant())
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));

        menu.setRestaurant(restaurant);
        return menu;
    }

}

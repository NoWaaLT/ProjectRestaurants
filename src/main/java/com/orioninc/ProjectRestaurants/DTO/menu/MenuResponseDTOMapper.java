package com.orioninc.ProjectRestaurants.DTO.menu;

import com.orioninc.ProjectRestaurants.model.Menu;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MenuResponseDTOMapper implements Function<Menu, MenuResponseDTO> {
    @Override
    public MenuResponseDTO apply(Menu menu) {
        return new MenuResponseDTO(
                menu.getMenuName(),
                menu.getRestaurantName()
        );
    }
}

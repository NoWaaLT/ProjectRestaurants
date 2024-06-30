package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.DTO.menu.MenuResponseDTO;
import com.orioninc.ProjectRestaurants.DTO.menu.MenuRequestDTO;
import com.orioninc.ProjectRestaurants.model.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {
    List<MenuResponseDTO> getAllMenus();
    List<MenuResponseDTO> getAllMenusByRestaurant(Long id);
    MenuResponseDTO getMenuById(Long id);
    Menu saveMenu(MenuRequestDTO menuRequestDTO);
    Menu updateMenu(MenuRequestDTO menuRequestDTO);
    void deleteMenu(Long id);

}

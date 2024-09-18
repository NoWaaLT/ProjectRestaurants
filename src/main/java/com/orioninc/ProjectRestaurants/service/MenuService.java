package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.dto.menu.MenuRequestDto;
import com.orioninc.ProjectRestaurants.dto.menu.MenuResponseDto;
import com.orioninc.ProjectRestaurants.model.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {
    List<MenuResponseDto> getAllMenus();
    List<MenuResponseDto> getAllMenusByRestaurant(Long id);
    MenuResponseDto getMenuById(Long id);
    MenuResponseDto saveMenu(MenuRequestDto menuRequestDTO);
    List<MenuResponseDto> saveMenuList(List<MenuRequestDto> menuList);
    MenuResponseDto updateMenu(MenuRequestDto menuRequestDTO);
    void deleteMenu(Long id);

}

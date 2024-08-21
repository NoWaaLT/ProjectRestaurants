package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.dto.menu.*;
import com.orioninc.ProjectRestaurants.exceptions.MenuNotFoundException;
import com.orioninc.ProjectRestaurants.model.Menu;
import com.orioninc.ProjectRestaurants.repository.MenuRepository;
import com.orioninc.ProjectRestaurants.service.MenuService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static com.orioninc.ProjectRestaurants.enums.AppText.*;

@AllArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

  private final MenuRepository menuRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MenuResponseDto> getAllMenus() {
    List<Menu> menuList = menuRepository.findAll();
    if (menuList.isEmpty()) {
      throw new MenuNotFoundException(MENUS_EMPTY);
    }
    return menuList.stream().map(MenuMapper.INSTANCE::menuToMenuResponseDto).toList();
  }

  @Transactional(readOnly = true)
  @Override
  public List<MenuResponseDto> getAllMenusByRestaurant(Long id) {
    Collection<Menu> menuList = menuRepository.findAllMenuByRestaurantId(id);
    if (menuList.isEmpty()) {
      throw new MenuNotFoundException(MENUS_EMPTY_BY_RESTAURANT, id);
    }
    return menuList.stream().map(MenuMapper.INSTANCE::menuToMenuResponseDto).toList();
  }

  @Transactional(readOnly = true)
  @Override
  public MenuResponseDto getMenuById(Long id) {
    return MenuMapper.INSTANCE.menuToMenuResponseDto(
        menuRepository
            .findById(id)
            .orElseThrow(() -> new MenuNotFoundException(MENU_BY_ID_NOT_FOUND, id)));
  }

  @Transactional
  @Override
  public Menu saveMenu(MenuRequestDto menuRequestDTO) {
    Menu menu = MenuMapper.INSTANCE.menuRequestDtoToMenu(menuRequestDTO);
    return menuRepository.save(menu);
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  @Override
  public MenuResponseDto updateMenu(MenuRequestDto menuRequestDTO) {
    Menu menuToUpdate = MenuMapper.INSTANCE.menuRequestDtoToMenu(menuRequestDTO);
    Menu existingMenu =
        menuRepository
            .findById(menuToUpdate.getId())
            .orElseThrow(
                () -> new MenuNotFoundException(MENU_BY_ID_NOT_FOUND, menuToUpdate.getId()));

    existingMenu.setMenuName(menuToUpdate.getMenuName());
    existingMenu.setRestaurant(menuToUpdate.getRestaurant());
    existingMenu.setRestaurantName(menuToUpdate.getRestaurantName());

    return MenuMapper.INSTANCE.menuToMenuResponseDto(existingMenu);
  }

  @Transactional
  @Override
  public void deleteMenu(Long id) {
    menuRepository.deleteById(id);
  }
}

package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.dto.menu.*;
import com.orioninc.ProjectRestaurants.exceptions.MenuNotFoundException;
import com.orioninc.ProjectRestaurants.exceptions.RestaurantNotFoundException;
import com.orioninc.ProjectRestaurants.model.Menu;
import com.orioninc.ProjectRestaurants.model.Restaurant;
import com.orioninc.ProjectRestaurants.repository.MenuRepository;
import com.orioninc.ProjectRestaurants.repository.RestaurantRepository;
import com.orioninc.ProjectRestaurants.service.MenuService;

import lombok.AllArgsConstructor;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static com.orioninc.ProjectRestaurants.enums.AppText.*;

@Service
public class MenuServiceImpl implements MenuService {

  public MenuServiceImpl(
      MenuRepository menuRepository,
      MenuMapper menuMapper,
      RestaurantRepository restaurantRepository,
      @Lazy MenuService menuService) {
    this.menuRepository = menuRepository;
    this.menuMapper = menuMapper;
    this.restaurantRepository = restaurantRepository;
    this.menuService = menuService;
  }

  private final MenuRepository menuRepository;
  private final MenuMapper menuMapper;
  private final RestaurantRepository restaurantRepository;
  private final MenuService menuService;

  @Transactional(readOnly = true)
  @Cacheable(value = "menus", cacheManager = "myCacheManager")
  @Override
  public List<MenuResponseDto> getAllMenus() {
    List<Menu> menuList = menuRepository.findAll();
    if (menuList.isEmpty()) {
      throw new MenuNotFoundException(MENUS_EMPTY);
    }
    return menuList.stream().map(menuMapper::menuToMenuResponseDto).toList();
  }

  // TODO Fix caching for all and single items

  @Transactional(readOnly = true)
  @Cacheable(value = "menus", cacheManager = "myCacheManager", key = "#id")
  @Override
  public List<MenuResponseDto> getAllMenusByRestaurant(Long id) {
    Collection<Menu> menuList = menuRepository.findAllMenuByRestaurantId(id);
    if (menuList.isEmpty()) {
      throw new MenuNotFoundException(MENUS_EMPTY_BY_RESTAURANT, id);
    }
    return menuList.stream().map(menuMapper::menuToMenuResponseDto).toList();
  }

  @Transactional(readOnly = true)
  @Override
  public MenuResponseDto getMenuById(Long id) {
    return menuMapper.menuToMenuResponseDto(
        menuRepository
            .findById(id)
            .orElseThrow(() -> new MenuNotFoundException(MENU_BY_ID_NOT_FOUND, id)));
  }

  @Transactional
  @Override
  public MenuResponseDto saveMenu(MenuRequestDto menuRequestDTO) {
    Restaurant restaurant =
        restaurantRepository
            .findById(menuRequestDTO.restaurant())
            .orElseThrow(
                () ->
                    new RestaurantNotFoundException(
                        RESTAURANT_BY_ID_NOT_FOUND, menuRequestDTO.restaurant()));
    Menu menu = menuMapper.menuRequestDtoToMenu(restaurant, menuRequestDTO);
    return menuMapper.menuToMenuResponseDto(menuRepository.save(menu));
  }

  @Transactional
  @Override
  public List<MenuResponseDto> saveMenuList(List<MenuRequestDto> menuList) {
    menuList.forEach(menuService::saveMenu);
    return menuMapper.toResponseList(menuList);
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  @CachePut(value = "menus", key = "#id")
  @Override
  public MenuResponseDto updateMenu(MenuRequestDto menuRequestDTO) {
    Menu menuToUpdate = menuMapper.menuRequestDtoToMenu(menuRequestDTO);
    Menu existingMenu =
        menuRepository
            .findById(menuToUpdate.getId())
            .orElseThrow(
                () -> new MenuNotFoundException(MENU_BY_ID_NOT_FOUND, menuToUpdate.getId()));

    existingMenu.setMenuName(menuToUpdate.getMenuName());
    existingMenu.setRestaurant(menuToUpdate.getRestaurant());
    existingMenu.setRestaurantName(menuToUpdate.getRestaurantName());

    return menuMapper.menuToMenuResponseDto(existingMenu);
  }

  @Transactional
  @CacheEvict(allEntries = true)
  @Override
  public void deleteMenu(Long id) {
    menuRepository.deleteById(id);
  }
}

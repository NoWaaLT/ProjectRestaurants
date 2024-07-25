package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.DTO.menu.MenuResponseDTO;
import com.orioninc.ProjectRestaurants.DTO.menu.MenuResponseDTOMapper;
import com.orioninc.ProjectRestaurants.DTO.menu.MenuRequestDTO;
import com.orioninc.ProjectRestaurants.model.Menu;
import com.orioninc.ProjectRestaurants.service.MenuService;

import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@AllArgsConstructor
public class MenuController {

  private final MenuService menuService;
  private final MenuResponseDTOMapper menuResponseDTOMapper;

  @PreAuthorize("hasPermission(#id, 'Menu', 'read')")
  @GetMapping
  public List<MenuResponseDTO> getAllMenus() {
    return menuService.getAllMenus();
  }

  @PreAuthorize("hasPermission(#id, 'Menu', 'read')")
  @GetMapping(value = "/restaurant-{id}")
  public List<MenuResponseDTO> getAllMenusByRestaurant(@PathVariable Long id) {
    return menuService.getAllMenusByRestaurant(id);
  }

  @PreAuthorize("hasPermission(#id, 'Menu', 'read')")
  @GetMapping(value = "/{id}")
  public MenuResponseDTO getMenuById(@PathVariable Long id) {
    return menuService.getMenuById(id);
  }

  @PreAuthorize("hasPermission(#id, 'Menu', 'create')")
  @PostMapping
  public Menu saveMenu(@RequestBody MenuRequestDTO menuRequestDTO) {
    return menuService.saveMenu(menuRequestDTO);
  }

  // TODO make id in path

  @PreAuthorize("hasPermission(#id, 'Menu', 'update')")
  @PutMapping(value = "/menus/update")
  public MenuResponseDTO updateMenu(@RequestBody MenuRequestDTO menuRequestDTO) {
    return menuResponseDTOMapper.apply(menuService.updateMenu(menuRequestDTO));
  }

  @PreAuthorize("hasPermission(#id, 'Menu', 'delete')")
  @DeleteMapping(value = "/{id}")
  public void deleteMenu(@PathVariable Long id) {
    menuService.deleteMenu(id);
  }
}

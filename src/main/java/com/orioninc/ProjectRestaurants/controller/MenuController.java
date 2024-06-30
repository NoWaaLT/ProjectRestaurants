package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.DTO.menu.MenuResponseDTO;
import com.orioninc.ProjectRestaurants.DTO.menu.MenuResponseDTOMapper;
import com.orioninc.ProjectRestaurants.DTO.menu.MenuRequestDTO;
import com.orioninc.ProjectRestaurants.model.Menu;
import com.orioninc.ProjectRestaurants.service.MenuService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@AllArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final MenuResponseDTOMapper menuResponseDTOMapper;

    @GetMapping(value = "/menus")
    public List<MenuResponseDTO> getAllMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping(value = "/{id}/menus")
    public List<MenuResponseDTO> getAllMenusByRestaurant(@PathVariable Long id) {
        return menuService.getAllMenusByRestaurant(id);
    }

    @GetMapping(value = "/menus/menu_{id}")
    public MenuResponseDTO getMenuById(@PathVariable Long id) {
        return menuService.getMenuById(id);
    }

    @PostMapping(value = "/menus/save")
        public Menu saveMenu(@RequestBody MenuRequestDTO menuRequestDTO) {
        return menuService.saveMenu(menuRequestDTO);
    }

    @PutMapping(value = "/menus/update")
    public MenuResponseDTO updateMenu(@RequestBody MenuRequestDTO menuRequestDTO) {
        return menuResponseDTOMapper.apply(menuService.updateMenu(menuRequestDTO));
    }

    @DeleteMapping(value = "/menus/delete/{id}")
    public void deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
    }

}

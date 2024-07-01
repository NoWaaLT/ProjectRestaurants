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
@RequestMapping("/api/restaurant")
@AllArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final MenuResponseDTOMapper menuResponseDTOMapper;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/menus")
    public List<MenuResponseDTO> getAllMenus() {
        return menuService.getAllMenus();
    }
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = "/{id}/menus")
    public List<MenuResponseDTO> getAllMenusByRestaurant(@PathVariable Long id) {
        return menuService.getAllMenusByRestaurant(id);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = "/menus/menu_{id}")
    public MenuResponseDTO getMenuById(@PathVariable Long id) {
        return menuService.getMenuById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/menus/save")
        public Menu saveMenu(@RequestBody MenuRequestDTO menuRequestDTO) {
        return menuService.saveMenu(menuRequestDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping(value = "/menus/update")
    public MenuResponseDTO updateMenu(@RequestBody MenuRequestDTO menuRequestDTO) {
        return menuResponseDTOMapper.apply(menuService.updateMenu(menuRequestDTO));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(value = "/menus/delete/{id}")
    public void deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
    }

}

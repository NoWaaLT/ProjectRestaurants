package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.dto.menu.MenuRequestDto;
import com.orioninc.ProjectRestaurants.dto.menu.MenuResponseDto;
import com.orioninc.ProjectRestaurants.dto.product.ProductAddDto;
import com.orioninc.ProjectRestaurants.model.Menu;
import com.orioninc.ProjectRestaurants.service.MenuService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@AllArgsConstructor
public class MenuController {

  private final MenuService menuService;

  @PreAuthorize("hasPermission(#id, 'Menu', 'read')")
  @GetMapping
  public ResponseEntity<List<MenuResponseDto>> getAllMenus() {
    return new ResponseEntity<>(menuService.getAllMenus(), HttpStatus.OK);
  }

  @PreAuthorize("hasPermission(#id, 'Menu', 'read')")
  @GetMapping(value = "/restaurant-{id}")
  public ResponseEntity<List<MenuResponseDto>> getAllMenusByRestaurant(@PathVariable Long id) {
    return new ResponseEntity<>(menuService.getAllMenusByRestaurant(id), HttpStatus.OK);
  }

  @PreAuthorize("hasPermission(#id, 'Menu', 'read')")
  @GetMapping(value = "/{id}")
  public ResponseEntity<MenuResponseDto> getMenuById(@PathVariable Long id) {
    return new ResponseEntity<>(menuService.getMenuById(id), HttpStatus.OK);
  }

  @PreAuthorize("hasPermission(#id, 'Menu', 'create')")
  @PostMapping(value = "/single")
  public ResponseEntity<MenuResponseDto> saveMenu(@RequestBody MenuRequestDto menuRequestDTO) {
    return new ResponseEntity<>(menuService.saveMenu(menuRequestDTO), HttpStatus.CREATED);
  }

  @PreAuthorize("hasPermission(#id, 'Menu', 'create')")
  @PostMapping(value = "/list")
  public ResponseEntity<List<MenuResponseDto>> saveProducts(
          @Valid @RequestBody List<MenuRequestDto> menuRequestDtoList) {
    return new ResponseEntity<>(menuService.saveMenuList(menuRequestDtoList), HttpStatus.CREATED);
  }

  @PreAuthorize("hasPermission(#id, 'Menu', 'update')")
  @PutMapping(value = "/menus/update")
  public ResponseEntity<MenuResponseDto> updateMenu(@RequestBody MenuRequestDto menuRequestDTO) {
    return new ResponseEntity<>(menuService.updateMenu(menuRequestDTO), HttpStatus.OK);
  }

  @PreAuthorize("hasPermission(#id, 'Menu', 'delete')")
  @DeleteMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Deleted succesfully!")
  public void deleteMenu(@PathVariable Long id) {
    menuService.deleteMenu(id);
  }
}

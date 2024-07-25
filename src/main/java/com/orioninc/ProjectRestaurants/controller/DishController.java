package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.DTO.dish.DishDTO;
import com.orioninc.ProjectRestaurants.DTO.dish.DishResponseDTOMapper;
import com.orioninc.ProjectRestaurants.model.Dish;
import com.orioninc.ProjectRestaurants.service.DishService;

import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
@AllArgsConstructor
public class DishController {

  private final DishService dishService;
  private final DishResponseDTOMapper dishResponseDTOMapper;

  @PreAuthorize("hasPermission(#id, 'Dish', 'read')")
  @GetMapping
  public List<DishDTO> getAllDishes() {
    return dishService.getAllDishes();
  }

  @PreAuthorize("hasPermission(#id, 'Dish', 'read')")
  @GetMapping(value = "/menu-{id}")
  public List<DishDTO> getAllDishesByMenuId(@PathVariable Long id) {
    return dishService.getAllDishesByMenuId(id);
  }

  @PreAuthorize("hasPermission(#id, 'Dish', 'read')")
  @GetMapping(value = "/{id}")
  public DishDTO getDishById(@PathVariable Long id) {
    return dishResponseDTOMapper.apply(dishService.getDishById(id));
  }

  @PreAuthorize("hasPermission(#id, 'Dish', 'create')")
  @PostMapping
  public Dish saveDish(@RequestBody DishDTO dishDTO) {
    return dishService.saveDish(dishDTO);
  }

  // TODO consider making update by id in uri

  @PreAuthorize("hasPermission(#id, 'Dish', 'update')")
  @PutMapping(value = "/dishes/update")
  public Dish updateDish(@RequestBody DishDTO dishDTO) {
    return dishService.updateDish(dishDTO);
  }

  @PreAuthorize("hasPermission(#id, 'Dish', 'delete')")
  @DeleteMapping(value = "/{id}")
  public void deleteDish(@PathVariable Long id) {
    dishService.deleteDish(id);
  }
}

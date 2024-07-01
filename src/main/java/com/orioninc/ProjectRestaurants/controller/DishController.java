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
@RequestMapping("/api/restaurant")
@AllArgsConstructor
public class DishController {

  private final DishService dishService;
  private final DishResponseDTOMapper dishResponseDTOMapper;

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @GetMapping(value = "/dishes/get")
  public List<DishDTO> getAllDishes() {
    return dishService.getAllDishes();
  }

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @GetMapping(value = "/dishes/{id}/get")
  public List<DishDTO> getAllDishesByMenuId(@PathVariable Long id) {
    return dishService.getAllDishesByMenuId(id);
  }

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @GetMapping(value = "/dishes/get/{id}")
  public DishDTO getDishById(@PathVariable Long id) {
    return dishResponseDTOMapper.apply(dishService.getDishById(id));
  }

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @PostMapping(value = "/dishes/save")
  public Dish saveDish(@RequestBody DishDTO dishDTO) {
    return dishService.saveDish(dishDTO);
  }

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @PutMapping(value = "/dishes/update")
  public Dish updateDish(@RequestBody DishDTO dishDTO) {
    return dishService.updateDish(dishDTO);
  }

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @DeleteMapping(value = "/dishes/delete")
  public void deleteDish(@PathVariable Long id) {
    dishService.deleteDish(id);
  }
}

package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.dto.dish.DishDto;
import com.orioninc.ProjectRestaurants.model.Dish;
import com.orioninc.ProjectRestaurants.service.DishService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
@AllArgsConstructor
public class DishController {

  private final DishService dishService;

  @PreAuthorize("hasPermission(#id, 'Dish', 'read')")
  @GetMapping
  public ResponseEntity<List<DishDto>> getAllDishes() {
    return new ResponseEntity<>(dishService.getAllDishes(), HttpStatus.OK);
  }

  @PreAuthorize("hasPermission(#id, 'Dish', 'read')")
  @GetMapping(value = "/menu/{id}")
  public ResponseEntity<List<DishDto>> getAllDishesByMenuId(@PathVariable Long id) {
    return new ResponseEntity<>(dishService.getAllDishesByMenuId(id), HttpStatus.OK);
  }

  @PreAuthorize("hasPermission(#id, 'Dish', 'read')")
  @GetMapping(value = "/{id}")
  public ResponseEntity<DishDto> getDishById(@PathVariable Long id) {
    return new ResponseEntity<>(dishService.getDishById(id), HttpStatus.OK);
  }

  @PreAuthorize("hasPermission(#id, 'Dish', 'create')")
  @PostMapping
  public ResponseEntity<DishDto> saveDish(@Valid @RequestBody DishDto dishDTO) {
    return new ResponseEntity<>(dishService.saveDish(dishDTO), HttpStatus.CREATED);
  }

  @PreAuthorize("hasPermission(#id, 'Dish', 'update')")
  @PutMapping(value = "/dishes/update")
  public ResponseEntity<Dish> updateDish(@Valid @RequestBody DishDto dishDTO) {
    return new ResponseEntity<>(dishService.updateDish(dishDTO), HttpStatus.OK);
  }

  @PreAuthorize("hasPermission(#id, 'Dish', 'delete')")
  @DeleteMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Deleted succesfully!")
  public void deleteDish(@PathVariable Long id) {
    dishService.deleteDish(id);
  }
}

package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.DTO.restaurant.RestaurantDTO;
import com.orioninc.ProjectRestaurants.model.Restaurant;
import com.orioninc.ProjectRestaurants.service.RestaurantService;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping(value = "/api/get")
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping(value = "/api/get/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @PostMapping(value = "/api/save")
    public Restaurant saveRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.saveRestaurant(restaurantDTO);
    }

    @PutMapping(value = "/api/update")
    Restaurant updateRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.updateRestaurant(restaurantDTO);
    }

    @DeleteMapping(value = "/api/delete/{id}")
    void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
    }

}

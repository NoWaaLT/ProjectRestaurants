package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.DTO.restaurant.RestaurantDTO;
import com.orioninc.ProjectRestaurants.model.Restaurant;
import com.orioninc.ProjectRestaurants.service.RestaurantService;


import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = "/get")
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }


    @GetMapping(value = "/api/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/save")
    public Restaurant saveRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.saveRestaurant(restaurantDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping(value = "/update")
    Restaurant updateRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.updateRestaurant(restaurantDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(value = "/delete/{id}")
    void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
    }

}

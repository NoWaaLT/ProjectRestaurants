package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.DTO.restaurant.RestaurantDTO;
import com.orioninc.ProjectRestaurants.model.Restaurant;
import com.orioninc.ProjectRestaurants.service.RestaurantService;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PreAuthorize("hasPermission(#id, 'Restaurant', 'read')")
    @GetMapping
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @PreAuthorize("hasPermission(#id, 'Restaurant', 'read')")
    @GetMapping(value = "/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @PreAuthorize("hasPermission(#id, 'Restaurant', 'create')")
    @PostMapping
    public Restaurant saveRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.saveRestaurant(restaurantDTO);
    }

    // TODO Make it update by id

    @PreAuthorize("hasPermission(#id, 'Restaurant', 'update')")
    @PutMapping(value = "/update")
    Restaurant updateRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.updateRestaurant(restaurantDTO);
    }

    @PreAuthorize("hasPermission(#id, 'Restaurant', 'delete')")
    @DeleteMapping(value = "/{id}")
    void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
    }

}

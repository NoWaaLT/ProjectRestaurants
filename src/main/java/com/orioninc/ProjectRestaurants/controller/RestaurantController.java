package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.dto.restaurant.RestaurantDto;
import com.orioninc.ProjectRestaurants.model.Restaurant;
import com.orioninc.ProjectRestaurants.service.RestaurantService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        return new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
    }

    @PreAuthorize("hasPermission(#id, 'Restaurant', 'read')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable Long id) {
        return new ResponseEntity<>(restaurantService.getRestaurantById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasPermission(#id, 'Restaurant', 'create')")
    @PostMapping
    public ResponseEntity<Restaurant> saveRestaurant(@RequestBody RestaurantDto restaurantDTO) {
        return new ResponseEntity<>(restaurantService.saveRestaurant(restaurantDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasPermission(#id, 'Restaurant', 'update')")
    @PutMapping(value = "/update")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody RestaurantDto restaurantDTO) {
        return new ResponseEntity<>(restaurantService.updateRestaurant(restaurantDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasPermission(#id, 'Restaurant', 'delete')")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Deleted successfully!")
    void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
    }

}

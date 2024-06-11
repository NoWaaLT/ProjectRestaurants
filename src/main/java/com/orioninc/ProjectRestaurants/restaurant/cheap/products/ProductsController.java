package com.orioninc.ProjectRestaurants.restaurant.cheap.products;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

    @GetMapping("/api/restaurants/cheap/products")
    public String getProducts() {
        return null;
    }

}

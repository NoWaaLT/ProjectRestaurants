package com.orioninc.ProjectRestaurants.restaurant.cheap.products;

import com.orioninc.ProjectRestaurants.restaurant.Products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CheapProductsController {
    @Autowired
    private CheapProductsRepository cheapProductsRepository;

    @GetMapping(value = "/api/restaurants/cheap/products")
    public List<CheapProducts> getProducts() {
        return cheapProductsRepository.findAll();
    }

    @PostMapping(value = "/api/restaurants/cheap/products/save")
    public String saveCheapProducts(@RequestBody CheapProducts cheapProducts) {
        cheapProductsRepository.save(cheapProducts);
        return "Saved...";
    }

    @GetMapping(value = "/")
    public String sayHello() {
        return "Hello";
    }

}

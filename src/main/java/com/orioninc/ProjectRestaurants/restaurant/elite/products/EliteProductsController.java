package com.orioninc.ProjectRestaurants.restaurant.elite.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants/elite/products")
public class EliteProductsController {

    @Autowired
    private EliteProductsRepository eliteProductsRepository;

    @GetMapping(value = "/get")
    public List<EliteProducts> getProducts() {
        return eliteProductsRepository.findAll();
    }

    @PostMapping(value = "/save")
    public String saveEliteProducts(@RequestBody EliteProducts eliteProducts) {
        eliteProductsRepository.save(eliteProducts);
        return "Saved...";
    }

    @PutMapping(value = "/update/{id}")
    public String updateEliteProducts(@PathVariable long id, @RequestBody EliteProducts eliteProducts) {
        EliteProducts updatedEliteProducts = eliteProductsRepository.findById(id).get();
        updatedEliteProducts.setProductName(eliteProducts.getProductName());
        updatedEliteProducts.setProductPrice(updatedEliteProducts.getProductPrice());
        updatedEliteProducts.setProductBalance(updatedEliteProducts.getProductBalance());
        eliteProductsRepository.save(updatedEliteProducts);
        return "Updated..";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteEliteProduct(@PathVariable long id) {
        Optional<EliteProducts> eliteProductsOptional = eliteProductsRepository.findById(id);
        if(eliteProductsOptional.isPresent()) {
            EliteProducts deleteEliteProduct = eliteProductsOptional.get();
            eliteProductsRepository.delete(deleteEliteProduct);
            return "Delete product with the id:" + id;
        }
        else {
            return "Product not found";
        }
    }

}

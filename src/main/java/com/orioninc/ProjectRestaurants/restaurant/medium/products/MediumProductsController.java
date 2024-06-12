package com.orioninc.ProjectRestaurants.restaurant.medium.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants/medium/products")
public class MediumProductsController {
    @Autowired
    private MediumProductsRepository mediumProductsRepository;

    @GetMapping(value = "/get")
    public List<MediumProducts> getProducts() {
        return mediumProductsRepository.findAll();
    }

    @PostMapping(value = "/save")
    public String saveMediumProducts(@RequestBody MediumProducts mediumProducts) {
        mediumProductsRepository.save(mediumProducts);
        return "Saved...";
    }

    @PutMapping(value = "/update/{id}")
    public String updateMediumProducts(@PathVariable long id, @RequestBody MediumProducts mediumProducts) {
        Optional<MediumProducts> mediumProductsOptional = mediumProductsRepository.findById(id);
        if (mediumProductsOptional.isPresent()) {
            MediumProducts existingMediumProducts = mediumProductsOptional.get();
            existingMediumProducts.setProductName(mediumProducts.getProductName());
            existingMediumProducts.setProductPrice(mediumProducts.getProductPrice());
            existingMediumProducts.setProductBalance(mediumProducts.getProductBalance());
            mediumProductsRepository.save(existingMediumProducts);
            return "Updated..";
        } else {
            return "Product not found";
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteMediumProduct(@PathVariable long id) {
        Optional<MediumProducts> mediumProductsOptional = mediumProductsRepository.findById(id);
        if(mediumProductsOptional.isPresent()) {
            MediumProducts deleteMediumProduct = mediumProductsOptional.get();
            mediumProductsRepository.delete(deleteMediumProduct);
            return "Delete product with the id:" + id;
        }
        else {
            return "Product not found";
        }
    }
}

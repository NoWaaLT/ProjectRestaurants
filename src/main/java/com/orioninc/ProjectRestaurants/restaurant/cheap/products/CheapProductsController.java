package com.orioninc.ProjectRestaurants.restaurant.cheap.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants/cheap/products")
public class CheapProductsController {
    @Autowired
    private CheapProductsRepository cheapProductsRepository;

    @GetMapping(value = "/get")
    public List<CheapProducts> getProducts() {
        return cheapProductsRepository.findAll();
    }

    @PostMapping(value = "/save")
    public String saveCheapProducts(@RequestBody CheapProducts cheapProducts) {
        cheapProductsRepository.save(cheapProducts);
        return "Saved...";
    }

    @PutMapping(value = "/update/{id}")
    public String updateCheapProducts(@PathVariable long id, @RequestBody CheapProducts cheapProducts) {
        Optional<CheapProducts> cheapProductsOptional = cheapProductsRepository.findById(id);
        if (cheapProductsOptional.isPresent()) {
            CheapProducts existingCheapProducts = cheapProductsOptional.get();
            existingCheapProducts.setProductName(cheapProducts.getProductName());
            existingCheapProducts.setProductPrice(cheapProducts.getProductPrice());
            existingCheapProducts.setProductBalance(cheapProducts.getProductBalance());
            cheapProductsRepository.save(existingCheapProducts);
            return "Updated..";
        } else {
            return "Product not found";
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteCheapProduct(@PathVariable long id) {
        Optional<CheapProducts> cheapProductsOptional = cheapProductsRepository.findById(id);
        if(cheapProductsOptional.isPresent()) {
            CheapProducts deleteCheapProduct = cheapProductsOptional.get();
            cheapProductsRepository.delete(deleteCheapProduct);
            return "Delete product with the id:" + id;
        }
        else {
            return "Product not found";
        }
    }

    @GetMapping(value = "/")
    public String sayHello() {
        return "Hello";
    }

}

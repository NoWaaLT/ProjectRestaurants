package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.DTO.ProductDTO;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.service.ProductService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

//    @GetMapping(value = "/get")
//    public List<Product> findAllProduct() {
//        return productService.findAllProduct();
//    }

    @GetMapping(value = "/{id}/products/get")
    public List<ProductDTO> findAllProduct(@PathVariable("id") String id) {
        return productService.findAllProductByRestaurant(id);
    }

    @PostMapping(value = "{id}/products/save")
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping(value = "/update/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }

//    @GetMapping(value = "/")
//    public String sayHello() {
//        return "Hello";
//    }

}

package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.dto.ProductDTO;
import com.orioninc.ProjectRestaurants.dto.ProductDTOMapper;
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
    private final ProductDTOMapper productDTOMapper;

    @GetMapping(value = "/{id}/products/get")
    public List<ProductDTO> findAllProduct(@PathVariable("id") String id) {
        return productService.findAllProductByRestaurant(id);
    }

    @PostMapping(value = "/products/save")
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping(value = "/products/update")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        Product updatedProduct = productService.updateProduct(productDTO);
        return productDTOMapper.apply(updatedProduct);
    }

    @DeleteMapping(value = "/products/delete/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }

}

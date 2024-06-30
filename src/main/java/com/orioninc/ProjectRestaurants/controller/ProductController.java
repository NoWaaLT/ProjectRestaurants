package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.DTO.product.ProductDTO;
import com.orioninc.ProjectRestaurants.DTO.product.ProductResponseDTOMapper;
import com.orioninc.ProjectRestaurants.DTO.product.ProductWhDTO;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.service.ProductService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductResponseDTOMapper productResponseDTOMapper;

    @GetMapping(value = "/{id}/products/get")
    public List<ProductDTO> findAllProduct(@PathVariable("id") Long id) {
        return productService.getAllProductByRestaurant(id);
    }   // Works

    @GetMapping(value = "/products/get/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }   // Works

    @PostMapping(value = "/products/save")
    public Product saveProduct(@RequestBody ProductDTO productDTO) {

        return productService.saveProduct(productDTO);
    }  // Works

    @PostMapping(value = "/products/saveAll")
    public List<ProductDTO> saveProducts(@RequestBody List<ProductDTO> productDTOList) {
        return productService.saveProducts(productDTOList);
    }  // Works

    @PostMapping(value = "/products/warehouse-save")
    public Product saveProductFromWarehouse(@RequestBody ProductWhDTO productWhDTO) {
        return productService.saveProductFromWarehouse(productWhDTO);
    }  // Works

    @PutMapping(value = "/products/update")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        Product updatedProduct = productService.updateProduct(productDTO);
        return productResponseDTOMapper.apply(updatedProduct);
    }

    @DeleteMapping(value = "/products/delete/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }

    @GetMapping(value = "/welcome")
    public String welcome() {
        return "Welcome to Spring!";
    }

//    @GetMapping("warehouse/get")
//    public String getWarehouseInventory() {
//        return warehouseCustomerService.getJsonResponse();
//    }

}

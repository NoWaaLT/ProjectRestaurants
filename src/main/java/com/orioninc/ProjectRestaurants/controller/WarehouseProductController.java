package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.dto.product.ProductWhDto;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.model.warehouse.WarehouseProduct;
import com.orioninc.ProjectRestaurants.service.implementation.WarehouseProductsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
@AllArgsConstructor
public class WarehouseProductController {

    private final WarehouseProductsServiceImpl warehouseProductsService;
                                                                                                        /// TODO NOT CHECKED YET
    @GetMapping("/product")
    public Mono<WarehouseProduct> getProduct() {
        return warehouseProductsService.getWarehouseProduct();
    }

    @GetMapping("/products")
    public Flux<WarehouseProduct> getAllProducts() {
        return warehouseProductsService.getWarehouseAllProducts();
    }

    @PreAuthorize("hasPermission(#id, 'Product', 'create')")
    @PostMapping(value = "/single")
    public Product saveProductFromWarehouse(@RequestBody ProductWhDto productWhDTO) {
        return warehouseProductsService.saveProductFromWarehouse(productWhDTO);
    }

    @PreAuthorize("hasPermission(#id, 'Product', 'create')")
    @PostMapping(value = "/list")
    public List<ProductWhDto> saveProductsFromWarehouse(@RequestBody List<ProductWhDto> productsListWhDTO) {
        return warehouseProductsService.saveProductsFromWarehouse(productsListWhDTO);
    }
}

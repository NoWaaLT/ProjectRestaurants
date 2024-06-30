package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.model.warehouse.WarehouseProduct;
import com.orioninc.ProjectRestaurants.service.implementation.WarehouseProductsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/warehouseProducts")
@AllArgsConstructor
public class WarehouseProductController {

    private final WarehouseProductsServiceImpl warehouseProductsService;

    @GetMapping("/getProduct")
    public Mono<WarehouseProduct> getProduct() {
        return warehouseProductsService.getWarehouseProduct();
    }

    @GetMapping("/getAllProducts")
    public Flux<WarehouseProduct> getAllProducts() {
        return warehouseProductsService.getWarehouseAllProducts();
    }
}

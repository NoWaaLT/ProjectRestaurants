package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.dto.product.ProductWhDto;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.model.warehouse.WarehouseProduct;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public interface WarehouseProductsService {

   Mono<WarehouseProduct> getWarehouseProduct();
   Flux<WarehouseProduct> getWarehouseAllProducts();
   Product saveProductFromWarehouse(ProductWhDto productWhDTO);
   List<ProductWhDto> saveProductsFromWarehouse(List<ProductWhDto> productsListWhDTO);
   float calculateAveragePrice(float oldPrice, float oldBalance, float currentPrice, float addition);
}

package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.model.warehouse.WarehouseProduct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface WarehouseProductsService {

   Mono<WarehouseProduct> getWarehouseProduct();
   Flux<WarehouseProduct> getWarehouseAllProducts();
}

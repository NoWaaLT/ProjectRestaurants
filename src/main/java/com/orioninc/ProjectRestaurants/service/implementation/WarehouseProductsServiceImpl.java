package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.model.warehouse.WarehouseProduct;

import com.orioninc.ProjectRestaurants.service.WarehouseProductsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class WarehouseProductsServiceImpl implements WarehouseProductsService {

    private final WebClient webClient;

    WebClient.Builder webClientBuilder = WebClient.builder();

//    @Scheduled(cron = "*/5 * * * * *")
//    public WarehouseProduct getWarehouseProduct() throws JsonProcessingException {
//
//        return new ObjectMapper().readValue(webClientBuilder.build()
//                .get()
//                .uri("http://10.1.11.26:8080/api/v1/products/get/1")
//                .retrieve().bodyToMono((String.class)).block(), WarehouseProduct.class);
//    }

    public Mono<WarehouseProduct> getWarehouseProduct() {
        return webClient.get()
                .uri("/api/v1/products/get/1")
                .retrieve()
                .bodyToMono(WarehouseProduct.class);
    }

//    @Scheduled(cron = "*/5 * * * * *")
    public Flux<WarehouseProduct> getWarehouseAllProducts() {
        return webClient.get()
                .uri("/api/v1/products/get-all")
                .retrieve()
                .bodyToFlux(WarehouseProduct.class);
    }

//    public Mono<List<WarehouseProduct>> getAllWarehouseProducts() {
//        return getWarehouseAllProducts()
//                .collectList();
//    }

}
package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.model.warehouse.WarehouseProduct;

import com.orioninc.ProjectRestaurants.service.WarehouseProductsService;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

@Service
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class WarehouseProductsServiceImpl implements WarehouseProductsService {

  private final WebClient webClient;

  WebClient.Builder webClientBuilder = WebClient.builder();

//  private static final Logger logger = LogManager.getLogger(WarehouseProductsServiceImpl.class);

//  private static Mono<String> testMono() {
//    return Mono.just("Hello reactive").log();
//  }

//  private static Flux<String> testFlux() {
//
//    //        return Flux.just("a", "v", "s")
//    //                .log();
//
//    Collection<String> listOfNothing = new ArrayList<>();
//    listOfNothing.add("1");
//    listOfNothing.add("3");
//    listOfNothing.add("2");
//
//    return Flux.fromIterable(listOfNothing).log();
//  }

//  private static Flux<String> testMap() {
//    Collection<String> listOfNothing = new ArrayList<>();
//    listOfNothing.add("1abc");
//    listOfNothing.add("3Sds");
//    listOfNothing.add("2Sds");
//
//    Flux<String> flux = Flux.fromIterable(listOfNothing);
//
//    return flux.map(String::toUpperCase);
//  }

//  private static Flux<String> testFlatMap() {
//    Collection<String> listOfNothing = new ArrayList<>();
//    listOfNothing.add("1abc");
//    listOfNothing.add("3Sds");
//    listOfNothing.add("2Sds");
//
//    Flux<String> flux = Flux.fromIterable(listOfNothing);
//
//    return flux.flatMap(data -> Mono.just(data.toUpperCase(Locale.ROOT)));
//  }

//  private static
//  Mono <Map<Integer, Integer>> testColectMap() {
//    Flux<Integer> flux = Flux.just(1, 2, 3 ,4 ,5 ,6 ,7 ,8);
//    return flux.collectMap(data -> data, data -> data * data);
//  }
//
//  @Scheduled(cron = "*/15 * * * * *")
//  private void justTest() {
//    testColectMap().subscribe(logger::info);
//  }

//  private static Flux<String> testSkip() {
//    Flux<String> flux = Flux.just("abc", "efg", "gjk", "uiu");
//    flux.collectList()
//    return flux.delayElements(Duration.ofSeconds(5)); // how much skips
//  }

  //    @Scheduled(cron = "*/5 * * * * *")
  //    public WarehouseProduct getWarehouseProduct() throws JsonProcessingException {
  //
  //        return new ObjectMapper().readValue(webClientBuilder.build()
  //                .get()
  //                .uri("http://10.1.11.26:8080/api/v1/products/get/1")
  //                .retrieve().bodyToMono((String.class)).block(), WarehouseProduct.class);
  //    }

  public Mono<WarehouseProduct> getWarehouseProduct() {
    return webClient
        .get()
        .uri("/api/v1/products/get/1")
        .retrieve()
        .bodyToMono(WarehouseProduct.class);
  }

  //    @Scheduled(cron = "*/5 * * * * *")
  public Flux<WarehouseProduct> getWarehouseAllProducts() {
    return webClient
        .get()
        .uri("/api/v1/products/get-all")
        .retrieve()
        .bodyToFlux(WarehouseProduct.class);
  }

  //    public Mono<List<WarehouseProduct>> getAllWarehouseProducts() {
  //        return getWarehouseAllProducts()
  //                .collectList();
  //    }

}

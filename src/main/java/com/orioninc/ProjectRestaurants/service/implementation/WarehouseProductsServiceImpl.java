package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.dto.product.*;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.model.warehouse.WarehouseProduct;

import com.orioninc.ProjectRestaurants.repository.ProductRepository;
import com.orioninc.ProjectRestaurants.service.WarehouseProductsService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class WarehouseProductsServiceImpl implements WarehouseProductsService {

  private final WebClient webClient;
  private final ProductRepository productRepository;
  private final ProductWhDtoMapper productWhDTOMapper;
  private final ExpireServiceImpl expireService;

  WebClient.Builder webClientBuilder = WebClient.builder();
  private final ProductMapper productMapper;

  @Override
  @Transactional
  public Product saveProductFromWarehouse(ProductWhDto productWhDTO) {
    Optional<Product> existingProductOptional =
        productRepository.findProductByProductNameAndRestaurantId(
            productWhDTO.productName(), productWhDTO.restaurant());

    if (existingProductOptional.isPresent()) {
      Product existingProduct = existingProductOptional.get();
      Product productToUpdate = productWhDTOMapper.apply(productWhDTO);
      existingProduct.setProductBalance(
          existingProduct.getProductBalance() + productToUpdate.getProductBalance());

      float newProductPrice =
          calculateAveragePrice(
              existingProduct.getProductPrice(),
              existingProduct.getProductBalance(),
              productToUpdate.getProductPrice(),
              productToUpdate.getProductBalance());
      existingProduct.setProductPrice(newProductPrice); // Average of prices/balances

      productRepository.save(existingProduct);

        expireService.saveExpire(
            existingProduct, productToUpdate.getProductBalance()); // Create expire record in table

      return existingProduct;

    } else {
      return productMapper.productAddDtoToProduct(
          new ProductAddDto(
              productWhDTO.productName(),
              productWhDTO.productPrice(),
              productWhDTO.productBalance(),
              productWhDTO.restaurant(),
              productWhDTO.productBalance(), // Setting minimum balance to current balance
              productWhDTO.productExpiration()));
    }
  }

  @Override
  @Transactional
  public List<ProductWhDto> saveProductsFromWarehouse(List<ProductWhDto> productsListWhDTO) {
    productsListWhDTO.forEach(this::saveProductFromWarehouse);
    return productsListWhDTO;
  }

  @Override
  public float calculateAveragePrice(
      float oldPrice, float oldBalance, float currentPrice, float addition) {
    float avgPrice = (oldBalance * oldPrice + addition * currentPrice) / (oldBalance + addition);
    BigDecimal roundedAvgPrice = BigDecimal.valueOf(avgPrice).setScale(2, RoundingMode.DOWN);
    return roundedAvgPrice.floatValue();
  }

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

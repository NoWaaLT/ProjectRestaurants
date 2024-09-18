package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.dto.product.*;
import com.orioninc.ProjectRestaurants.exceptions.ProductNotFoundException;
import com.orioninc.ProjectRestaurants.exceptions.RestaurantNotFoundException;
import com.orioninc.ProjectRestaurants.model.Expire;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.model.Restaurant;
import com.orioninc.ProjectRestaurants.repository.ExpireRepository;
import com.orioninc.ProjectRestaurants.repository.ProductRepository;
import com.orioninc.ProjectRestaurants.repository.RestaurantRepository;
import com.orioninc.ProjectRestaurants.service.ExpireService;
import com.orioninc.ProjectRestaurants.service.ProductService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.orioninc.ProjectRestaurants.enums.AppText.PRODUCT_BY_ID_NOT_FOUND;
import static com.orioninc.ProjectRestaurants.enums.AppText.PRODUCT_IN_RESTAURANT_BY_ID_NOT_FOUND;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final RestaurantRepository restaurantRepository;
  private final ExpireService expireService;
  private final ProductService productService;
  private final ExpireRepository expireRepository;

  @Autowired
  public ProductServiceImpl(
      ProductRepository productRepository,
      RestaurantRepository restaurantRepository,
      ExpireService expireService,
      @Lazy ProductService productService,
      ExpireRepository expireRepository) {
    this.productRepository = productRepository;
    this.restaurantRepository = restaurantRepository;
    this.expireService = expireService;
    this.productService = productService;
    this.expireRepository = expireRepository;
  }

  @Transactional(readOnly = true)
  @Cacheable(value = "productsCache", cacheManager = "myCacheManager", key = "#restaurantId")
  @Override
  public List<ProductDto> getAllProductByRestaurantId(Long restaurantId) { // O
    Collection<Product> productList = productRepository.findAllByRestaurantId(restaurantId);
    if (productList.isEmpty()) {
      throw new ProductNotFoundException(PRODUCT_IN_RESTAURANT_BY_ID_NOT_FOUND, restaurantId);
    }

    return productList.stream().map(ProductMapper.INSTANCE::productToProductDto).toList();
  }

  @Transactional(readOnly = true)
  @Override
  public ProductDto getProductById(Long id) { // O
    return productRepository
        .findById(id)
        .map(ProductMapper.INSTANCE::productToProductDto)
        .orElseThrow(() -> new ProductNotFoundException(PRODUCT_BY_ID_NOT_FOUND, id));
  }

  @Transactional
  @Override
  public ProductDto saveProduct(ProductAddDto productAddDTO) { // O
    Product product = ProductMapper.INSTANCE.productAddDtoToProduct(productAddDTO);

    Restaurant restaurant =
        restaurantRepository
            .findById(product.getRestaurant().getId())
            .orElseThrow(
                () ->
                    new RestaurantNotFoundException(
                        PRODUCT_IN_RESTAURANT_BY_ID_NOT_FOUND, productAddDTO.restaurant()));

    product.setRestaurant(restaurant);
    Product savedProduct = productRepository.save(product);
    expireService.saveExpire(savedProduct, savedProduct.getProductBalance());

    return ProductMapper.INSTANCE.productToProductDto(savedProduct);
  }

  @Override
  @Transactional
  public List<ProductAddDto> saveProducts(@NotNull List<ProductAddDto> productAddDtoList) {
    productAddDtoList.forEach(productService::saveProduct);
    return productAddDtoList;
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  @CachePut(
      value = "productsCache",
      key = "#productDto.restaurant",
      cacheManager = "myCacheManager")
  @Override
  public Product updateProduct(ProductDto productDto) {
    Product productToUpdate = ProductMapper.INSTANCE.productDtoToProduct(productDto);

    Product existingProduct =
        productRepository
            .findById(productToUpdate.getId())
            .orElseThrow(
                () ->
                    new ProductNotFoundException(PRODUCT_BY_ID_NOT_FOUND, productToUpdate.getId()));

    existingProduct.setProductName(productToUpdate.getProductName());
    existingProduct.setProductPrice(productToUpdate.getProductPrice());

    float productBalance = productToUpdate.getProductBalance();
    float currentProductBalance = existingProduct.getProductBalance();

    if (productBalance < currentProductBalance) {
      Optional<List<Expire>> expireList =
          expireRepository.findAllExpireByProductId(productDto.id());
      if (expireList.isPresent()) {
        float balance = currentProductBalance - productBalance;

        List<Expire> listOfExpires = expireList.get();
        productService.updateExpire(balance, listOfExpires);
      }
    }

    if (productBalance > currentProductBalance) {
      float balance = productBalance - currentProductBalance;

      if (productToUpdate.getProductExpiration() > 0) {
        expireService.saveExpire(productToUpdate, balance);
      }
    }

    existingProduct.setProductBalance(productBalance);
    existingProduct.setRestaurant(productToUpdate.getRestaurant());

    return productRepository.save(existingProduct);
  }

  @Transactional
  @CacheEvict(key = "#id", cacheManager = "customCacheManager")
  @Override
  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }

  @Override
  @Transactional
  public void updateExpire(float balance, List<Expire> expireList) {
    int earliestExpireId = expireService.getEarliestExpire(expireList);

    if (earliestExpireId >= 0) {
      float batch = expireList.get(earliestExpireId).getBatchQuantity();

      if (balance < batch) {
        batch -= balance;
        expireList.get(earliestExpireId).setBatchQuantity(batch);
        expireRepository.setAmountAndRemoved(
            expireList.get(earliestExpireId).getBatchQuantity(),
            expireList.get(earliestExpireId).getRemovedProduct(),
            (long) earliestExpireId);
      } else if (balance == batch) {
        batch = 0;
        expireList.get(earliestExpireId).setRemovedProduct(true);
        expireList.get(earliestExpireId).setBatchQuantity(batch);
        expireRepository.setAmountAndRemoved(
            expireList.get(earliestExpireId).getBatchQuantity(),
            expireList.get(earliestExpireId).getRemovedProduct(),
            (long) earliestExpireId);
      } else {
        balance -= batch;
        batch = 0;
        expireList.get(earliestExpireId).setRemovedProduct(true);
        expireList.get(earliestExpireId).setBatchQuantity(batch);
        expireRepository.setAmountAndRemoved(
            expireList.get(earliestExpireId).getBatchQuantity(),
            expireList.get(earliestExpireId).getRemovedProduct(),
            (long) earliestExpireId);
        productService.updateExpire(balance, expireList);
      }
    }
  }
}

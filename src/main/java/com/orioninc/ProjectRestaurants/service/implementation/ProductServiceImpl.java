package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.dto.expire.ExpireResponseDto;
import com.orioninc.ProjectRestaurants.dto.product.*;
import com.orioninc.ProjectRestaurants.exceptions.ProductNotFoundException;
import com.orioninc.ProjectRestaurants.exceptions.RestaurantNotFoundException;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.model.Restaurant;
import com.orioninc.ProjectRestaurants.repository.ProductRepository;
import com.orioninc.ProjectRestaurants.repository.RestaurantRepository;
import com.orioninc.ProjectRestaurants.service.ExpireService;
import com.orioninc.ProjectRestaurants.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.orioninc.ProjectRestaurants.enums.AppText.PRODUCT_BY_ID_NOT_FOUND;
import static com.orioninc.ProjectRestaurants.enums.AppText.PRODUCT_IN_RESTAURANT_BY_ID_NOT_FOUND;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final RestaurantRepository restaurantRepository;
  private final ExpireService expireService;
  private final ProductServiceImpl productService;

  @Autowired
  public ProductServiceImpl(
      ProductRepository productRepository,
      RestaurantRepository restaurantRepository,
      ExpireService expireService,
      @Lazy ProductServiceImpl productService) {
    this.productRepository = productRepository;
    this.restaurantRepository = restaurantRepository;
    this.expireService = expireService;
    this.productService = productService;
  }

  @Transactional(readOnly = true)
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

    if (product.getProductExpiration() > 0) {
      expireService.saveExpire(savedProduct, savedProduct.getProductBalance());
    }

    return ProductMapper.INSTANCE.productToProductDto(savedProduct);
  }

  @Override
  @Transactional
  public List<ProductAddDto> saveProducts(List<ProductAddDto> productAddDtoList) {
    productAddDtoList.forEach(productService::saveProduct);
    return productAddDtoList;
  }

  @Override
  @Transactional(isolation = Isolation.READ_COMMITTED)
  public Product updateProduct(ProductDto productDTO) {
    Product productToUpdate = ProductMapper.INSTANCE.productDtoToProduct(productDTO);

    Product existingProduct =
        productRepository
            .findById(productToUpdate.getId())
            .orElseThrow(
                () ->
                    new ProductNotFoundException(PRODUCT_BY_ID_NOT_FOUND, productToUpdate.getId()));

    //    existingProduct.setProductName(productToUpdate.getProductName());
    //    existingProduct.setProductPrice(productToUpdate.getProductPrice());
    //
    //    if (!productToUpdate.getProductBalance().equals(existingProduct.getProductBalance())
    //        && productToUpdate.getProductBalance() < existingProduct.getProductBalance()) {
    //
    //      Optional<List<ExpireResponseDTO>> expireList =
    //          productExpireService.findExpiresByProductId(productDTO.id());
    //
    //      if (expireList.isPresent()) {
    //        float balance = existingProduct.getProductBalance() -
    // productToUpdate.getProductBalance();
    //        List<ExpireResponseDTO> listOfExpires = expireList.get();
    //        while (balance > 0) {
    //          Long expireDateId = findOldestDate(listOfExpires);
    //
    //          float quantityInBatch =
    //              productExpireService
    //                  .getProductExpireById(expireDateId)
    //                  .batchQuantity(); // Get the quantity in batch
    //
    //          if (balance < quantityInBatch) {
    //            quantityInBatch -= balance;
    //            balance = 0;
    //            expireRepositoryImpl.editExpireData(quantityInBatch, false, expireDateId);
    //          } else if (balance == quantityInBatch) {
    //            quantityInBatch = 0;
    //            balance = 0;
    //            expireRepositoryImpl.editExpireData(quantityInBatch, true, expireDateId);
    //          } else {
    //            balance -= quantityInBatch;
    //            quantityInBatch = 0;
    //            expireRepositoryImpl.editExpireData(quantityInBatch, true, expireDateId);
    //          }
    //        }
    //      }
    //    }
    //
    //    existingProduct.setProductBalance(productToUpdate.getProductBalance());
    //    existingProduct.setRestaurant(productToUpdate.getRestaurant());

    return productRepository.save(existingProduct);
  }

  @Transactional
  @Override
  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }

  @Override
  public Long findOldestDate(List<ExpireResponseDto> listOfExpires) {
    Date date = new Date();
    Long oldestExpireId = 0L;
    for (ExpireResponseDto expireResponseDTO : listOfExpires) {
      if (date.after(expireResponseDTO.expireDate()) && !expireResponseDTO.removedProduct()) {
        date = expireResponseDTO.expireDate();
        oldestExpireId = expireResponseDTO.id();
      }
    }

    return oldestExpireId;
  }
}

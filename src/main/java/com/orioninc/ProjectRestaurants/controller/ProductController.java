package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.dto.product.ProductAddDto;
import com.orioninc.ProjectRestaurants.dto.product.ProductDto;
import com.orioninc.ProjectRestaurants.dto.product.ProductMapper;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.service.ProductService;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@CacheConfig(cacheNames = "products")
public class ProductController {

  private final ProductService productService;

  @PreAuthorize("hasPermission(#id, 'Product', 'read')")
  @GetMapping(value = "/restaurant/{id}")
  @Cacheable(key = "#id")
  public ResponseEntity<List<ProductDto>> findAllProductsByRestaurantId(
      @PathVariable("id") Long id) {
    List<ProductDto> productList = productService.getAllProductByRestaurantId(id);
    return new ResponseEntity<>(productList, HttpStatus.OK);
  }

  @PreAuthorize("hasPermission(#id, 'Product', 'read')")
  @GetMapping(value = "/{id}")
  public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
    return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
  }

  @PreAuthorize("hasPermission(#id, 'Product', 'create')")
  @PostMapping(value = "/single")
  public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductAddDto productAddDTO) {
    return new ResponseEntity<>(productService.saveProduct(productAddDTO), HttpStatus.OK);
  }

  @PreAuthorize("hasPermission(#id, 'Product', 'create')")
  @PostMapping(value = "/list")
  public ResponseEntity<List<ProductAddDto>> saveProducts(
      @Valid @RequestBody List<ProductAddDto> productsListAddDTO) {
    return new ResponseEntity<>(productService.saveProducts(productsListAddDTO), HttpStatus.OK);
  }

  @PreAuthorize("hasPermission(#id, 'Product', 'update')")
  @PutMapping
  @CachePut(key = "#id")
  public ResponseEntity<ProductDto> updateProduct(
      @Valid @RequestBody ProductDto productDTO) { // TODO doesn't work
    Product updatedProduct = productService.updateProduct(productDTO);
    return new ResponseEntity<>(
        ProductMapper.INSTANCE.productToProductDto(updatedProduct), HttpStatus.OK); // TODO export the mapper to service layer
  }

  @PreAuthorize("hasPermission(#id, 'Product', 'delete')")
  @DeleteMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Deleted successfully!")
  @CacheEvict(key = "#id")
  public void deleteProduct(@PathVariable long id) {
    productService.deleteProduct(id);
  }
}

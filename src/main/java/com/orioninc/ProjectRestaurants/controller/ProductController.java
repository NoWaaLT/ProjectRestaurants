package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.DTO.product.ProductDTO;
import com.orioninc.ProjectRestaurants.DTO.product.ProductResponseDTOMapper;
import com.orioninc.ProjectRestaurants.DTO.product.ProductWhDTO;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@Slf4j
public class ProductController {

  private final ProductService productService;
  private final ProductResponseDTOMapper productResponseDTOMapper;

  @PreAuthorize("hasPermission(#id, 'Product', 'read')")
  @GetMapping(value = "/restaurant-{id}")
  public List<ProductDTO> findAllProductsByRestaurantId(@PathVariable("id") Long id) {
    return productService.getAllProductByRestaurant(id);
  }

  @PreAuthorize("hasPermission(#id, 'Product', 'read')")
  @GetMapping(value = "/{id}")
  public ProductDTO getProductById(@PathVariable Long id) {
    return productService.getProductById(id);
  }

  // TODO if we have to add new product to the list of products, id not needed

  @PreAuthorize("hasPermission(#id, 'Product', 'create')")
  @PostMapping(value = "/products/save")
  public Product saveProduct(@RequestBody ProductDTO productDTO) {

    return productService.saveProduct(productDTO);
  }

  @PreAuthorize("hasPermission(#id, 'Product', 'create')")
  @PostMapping(value = "/products/saveAll")
  public List<ProductDTO> saveProducts(@RequestBody List<ProductDTO> productDTOList) {
    return productService.saveProducts(productDTOList);
  }


  // TODO ???

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @PostMapping(value = "/products/warehouse-save")
  public Product saveProductFromWarehouse(@RequestBody ProductWhDTO productWhDTO) {
    return productService.saveProductFromWarehouse(productWhDTO);
  }


  // TODO specify the id in url

  @PreAuthorize("hasPermission(#id, 'Product', 'update')")
  @PutMapping(value = "/products/update")
  public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
    Product updatedProduct = productService.updateProduct(productDTO);
    return productResponseDTOMapper.apply(updatedProduct);
  }



  @PreAuthorize("hasPermission(#id, 'Product', 'delete')")
  @DeleteMapping(value = "/{id}")
  public void deleteProduct(@PathVariable long id) {
    productService.deleteProduct(id);
  }

}

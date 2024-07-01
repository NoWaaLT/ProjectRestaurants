package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.DTO.product.ProductDTO;
import com.orioninc.ProjectRestaurants.DTO.product.ProductResponseDTOMapper;
import com.orioninc.ProjectRestaurants.DTO.product.ProductWhDTO;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.service.ProductService;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@AllArgsConstructor
public class ProductController {

  private final ProductService productService;
  private final ProductResponseDTOMapper productResponseDTOMapper;

  @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
  @GetMapping(value = "/{id}/products/get")
  public List<ProductDTO> findAllProduct(@PathVariable("id") Long id) {
    return productService.getAllProductByRestaurant(id);
  } // Works

  @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
  @GetMapping(value = "/products/get/{id}")
  public ProductDTO getProductById(@PathVariable Long id) {
    return productService.getProductById(id);
  } // Works

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @PostMapping(value = "/products/save")
  public Product saveProduct(@RequestBody ProductDTO productDTO) {

    return productService.saveProduct(productDTO);
  } // Works

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @PostMapping(value = "/products/saveAll")
  public List<ProductDTO> saveProducts(@RequestBody List<ProductDTO> productDTOList) {
    return productService.saveProducts(productDTOList);
  } // Works

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @PostMapping(value = "/products/warehouse-save")
  public Product saveProductFromWarehouse(@RequestBody ProductWhDTO productWhDTO) {
    return productService.saveProductFromWarehouse(productWhDTO);
  } // Works

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @PutMapping(value = "/products/update")
  public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
    Product updatedProduct = productService.updateProduct(productDTO);
    return productResponseDTOMapper.apply(updatedProduct);
  }

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @DeleteMapping(value = "/products/delete/{id}")
  public void deleteProduct(@PathVariable long id) {
    productService.deleteProduct(id);
  }

  @PreAuthorize(
      "hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_EMPLOYEE') || hasAuthority('ROLE_USER')")
  @GetMapping(value = "/welcome")
  public String welcome() {
    return "Welcome to Spring!";
  }

  //    @GetMapping("warehouse/get")
  //    public String getWarehouseInventory() {
  //        return warehouseCustomerService.getJsonResponse();
  //    }

}

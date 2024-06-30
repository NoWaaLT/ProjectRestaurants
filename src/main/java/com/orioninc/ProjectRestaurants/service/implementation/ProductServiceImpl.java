package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.DTO.product.*;
import com.orioninc.ProjectRestaurants.exceptions.ProductNotFoundException;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.model.Expire;
import com.orioninc.ProjectRestaurants.repository.ProductRepository;
import com.orioninc.ProjectRestaurants.repository.RestaurantRepository;
import com.orioninc.ProjectRestaurants.service.ProductExpireService;
import com.orioninc.ProjectRestaurants.service.ProductService;

import com.orioninc.ProjectRestaurants.utils.ExpireDateUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductResponseDTOMapper productResponseDTOMapper;
    private final ProductRequestDTOMapper productRequestDTOMapper;
    private final ProductExpireService productExpireService;
    private final ProductWhDTOMapper productWhDTOMapper;
    private final RestaurantRepository restaurantRepository;

    @Override
    public List<ProductDTO> getAllProductByRestaurant(Long type) {

        return productRepository.findAll()
                .stream()
                .filter((Product product) -> Objects.equals(product.getRestaurant().getId(), type))
                .map(productResponseDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return productResponseDTOMapper.apply(productRepository.findById(id).orElseThrow(()
                -> new ProductNotFoundException("Product is not found")));
    }


    @Override
    public Product saveProduct(ProductDTO productDTO) {

        Product product = productRequestDTOMapper.apply(productDTO);
        Product savedProduct = productRepository.save(product);

        if (product.getProductExpirable() > 0) {
            Expire expire = new Expire();
            expire.setProduct(savedProduct);

            Date newDate = ExpireDateUtil.expirationDate(product.getProductExpirable());
            expire.setExpireDate(newDate);

            expire.setBatchQuantity(product.getProductBalance());

            productExpireService.saveProductExpire(expire);
        }

        return savedProduct;
    }

    @Override
    public List<ProductDTO> saveProducts(List<ProductDTO> productDTOList) {  // TODO Returns id: null on response in JSON, db add record as required
        for (ProductDTO pro : productDTOList) {
            saveProduct(pro);
        }

        return productDTOList;
    }

    @Override
    @Transactional
    public Product saveProductFromWarehouse(ProductWhDTO productWhDTO) {

        Product productToUpdate = productWhDTOMapper.apply(productWhDTO);

        String productName = productToUpdate.getProductName();
        Long restaurantId = productToUpdate.getRestaurant().getId();

        Optional<Product> existingProductOptional =
                productRepository.findProductByNameAndRestaurant(productName, restaurantId);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setProductBalance(existingProduct.getProductBalance() +
                    productToUpdate.getProductBalance()
            );
            existingProduct.setProductPrice(productWhDTO.productPrice()); // Can be adjusted if necessary
            productRepository.save(existingProduct);
            checkProductEXP(existingProduct, productToUpdate);

            return existingProduct;
        } else {
            ProductDTO unlistedProduct = new ProductDTO(
                    null,
                    productWhDTO.productName(),
                    productWhDTO.productPrice(),
                    productWhDTO.productBalance(),
                    productWhDTO.restaurant(),
                    productWhDTO.productBalance(),      // Consider change in future
                    productWhDTO.productExpirable()
            );

            return saveProduct(unlistedProduct);
        }
    }

    @Override
    public void checkProductEXP(Product product, Product productBatch) {
        if (product.getProductExpirable() > 0) {
            Expire expire = new Expire();
            expire.setProduct(product);

            Date newDate = ExpireDateUtil.expirationDate(product.getProductExpirable());
            expire.setExpireDate(newDate);

            expire.setBatchQuantity(productBatch.getProductBalance());

            productExpireService.saveProductExpire(expire);
        }
    }

    @Override
    public Product updateProduct(ProductDTO productDTO) {
        Product productToUpdate = productRequestDTOMapper.apply(productDTO);
        Product existingProduct = productRepository.findById(productToUpdate.getId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found."));

        existingProduct.setProductName(productToUpdate.getProductName());
        existingProduct.setProductPrice(productToUpdate.getProductPrice());
        existingProduct.setProductBalance(productToUpdate.getProductBalance());
        existingProduct.setRestaurant(productToUpdate.getRestaurant());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}

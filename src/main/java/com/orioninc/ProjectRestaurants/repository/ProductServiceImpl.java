package com.orioninc.ProjectRestaurants.repository;

import com.orioninc.ProjectRestaurants.dto.ProductDTO;
import com.orioninc.ProjectRestaurants.dto.ProductDTOMapper;
import com.orioninc.ProjectRestaurants.dto.ProductRequestDTOMapper;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.model.Restaurant;
import com.orioninc.ProjectRestaurants.service.ProductService;

import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Primary

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;
    private final ProductRequestDTOMapper productRequestDTOMapper;
    private final RestaurantRepository restaurantRepository;


    @Override
    public List<ProductDTO> findAllProductByRestaurant(String type) {

        return productRepository.findAll()
                .stream()
                .filter((Product product) -> product.getRestaurant().getId().toString().equals(type))
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public Product saveProduct(Product product) {
        Optional<Restaurant> opt = restaurantRepository.findById(product.getRestaurant().getId());
        if(opt.isPresent()) {
            product.setRestaurant(opt.get());
        }
        else {
            throw new RuntimeException("Restaurant not found");
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(ProductDTO productDTO) {
        Product productToUpdate = productRequestDTOMapper.apply(productDTO);
        Product existingProduct = productRepository.findById(productToUpdate.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setProductName(productToUpdate.getProductName());
        existingProduct.setProductPrice(productToUpdate.getProductPrice());
        existingProduct.setProductBalance(productToUpdate.getProductBalance());
        existingProduct.setRestaurant(productToUpdate.getRestaurant());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

}

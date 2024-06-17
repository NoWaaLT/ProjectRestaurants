package com.orioninc.ProjectRestaurants.repository;

import com.orioninc.ProjectRestaurants.DTO.ProductDTO;
import com.orioninc.ProjectRestaurants.DTO.ProductDTOMapper;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.service.ProductService;

import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Service
@Primary

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;


    @Override
    public List<ProductDTO> findAllProductByRestaurant(String type) {

        return productRepository.findAll()
                .stream()
                .filter((Product product) -> product.getRestaurant().getId().toString().equals(type))
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

//    @Override
//    public ProductDTO getProduct(Long id) {
//        return productRepository.findById(id)
//                .stream()
//                .map();
//    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(long id, Product product) {
        return product;
    }

    @Override
    public void deleteProduct(long id) {
//        productRepository.deleteById();
    }

}

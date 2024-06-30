package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.DTO.expire.ExpireRequestDTO;
import com.orioninc.ProjectRestaurants.DTO.expire.ExpireRequestDTOMapper;
import com.orioninc.ProjectRestaurants.DTO.expire.ExpireResponseDTO;
import com.orioninc.ProjectRestaurants.DTO.expire.ExpireResponseDTOMapper;
import com.orioninc.ProjectRestaurants.exceptions.ExpireNotFoundException;
import com.orioninc.ProjectRestaurants.exceptions.ProductNotFoundException;
import com.orioninc.ProjectRestaurants.model.Expire;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.repository.ExpireRepository;
import com.orioninc.ProjectRestaurants.repository.ProductRepository;
import com.orioninc.ProjectRestaurants.service.ProductExpireService;
import com.orioninc.ProjectRestaurants.utils.ExpireDateUtil;

import jakarta.transaction.Transactional;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductExpireServiceImpl implements ProductExpireService {

    private final ExpireRepository expireRepository;
    private final ExpireResponseDTOMapper expireResponseDTOMapper;
    private final ExpireRequestDTOMapper expireRequestDTOMapper;
    private final ProductRepository productRepository;

    @Override
    public List<ExpireResponseDTO> getAllProductExpires() {
        return expireRepository.findAll()
                .stream()
                .map(expireResponseDTOMapper).collect(Collectors.toList());
    }

    @Override
    public ExpireResponseDTO getProductExpireById(Long id) {
        return expireResponseDTOMapper.apply(expireRepository.findById(id).orElseThrow(() ->
                new ExpireNotFoundException("Product in expires not found.")));
    }

    @Transactional  // We don't need it???
    @Override
    public Expire saveProductExpire(Expire expire) {
        return expireRepository.save(expire);
    }

    @Override
    public Expire updateProductExpire(ExpireRequestDTO expireRequestDTO) {

        Expire existingExpire = expireRepository.findById(expireRequestDTO.id())
                .orElseThrow(() -> new ExpireNotFoundException("Product in expire list not found."));

        existingExpire.setId(expireRequestDTO.id());
        existingExpire.setExpireDate(ExpireDateUtil.expirationDate(expireRequestDTO.expireDuration()));

        Product product = productRepository.findById(expireRequestDTO.productId())
                        .orElseThrow(() -> new ProductNotFoundException("Product not found."));

        existingExpire.setProduct(product);
        existingExpire.setBatchQuantity(expireRequestDTO.batchQuantity());

        return expireRepository.save(existingExpire);
    }

    @Override
    public void deleteProductExpire(long id) {
        expireRepository.deleteById(id);
    }
}

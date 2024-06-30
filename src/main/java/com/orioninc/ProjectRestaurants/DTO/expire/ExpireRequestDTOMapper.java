package com.orioninc.ProjectRestaurants.DTO.expire;

import com.orioninc.ProjectRestaurants.exceptions.ProductNotFoundException;
import com.orioninc.ProjectRestaurants.model.Expire;
import com.orioninc.ProjectRestaurants.model.Product;

import com.orioninc.ProjectRestaurants.repository.ProductRepository;
import com.orioninc.ProjectRestaurants.utils.ExpireDateUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@AllArgsConstructor
@Component
public class ExpireRequestDTOMapper implements Function<ExpireRequestDTO, Expire> {

    private final ProductRepository productRepository;

    @Override
    public Expire apply(ExpireRequestDTO expireRequestDTO) {
        Expire expire = new Expire();

        Date newDate = ExpireDateUtil.expirationDate(expireRequestDTO.expireDuration());
        expire.setExpireDate(newDate);

        expire.setBatchQuantity(expireRequestDTO.batchQuantity());

        Product product = productRepository.findById(expireRequestDTO.productId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        expire.setProduct(product);

        return expire;
    }
}

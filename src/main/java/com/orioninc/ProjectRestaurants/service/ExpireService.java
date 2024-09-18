package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.dto.expire.ExpireRequestDto;
import com.orioninc.ProjectRestaurants.dto.expire.ExpireResponseDto;
import com.orioninc.ProjectRestaurants.model.Expire;
import com.orioninc.ProjectRestaurants.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpireService {
    List<ExpireResponseDto> getAllProductExpires();
    ExpireResponseDto getProductExpireById(Long id);
    Expire saveProductExpire(Expire expire);
    Expire updateProductExpire(ExpireRequestDto expireRequestDTO);
    void deleteProductExpire(long id);
    void saveExpire(Product product, Float productBatch);
    Integer getEarliestExpire(List<Expire> listOfExpires);
}
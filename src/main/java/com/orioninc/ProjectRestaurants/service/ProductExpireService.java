package com.orioninc.ProjectRestaurants.service;

import com.orioninc.ProjectRestaurants.DTO.expire.ExpireRequestDTO;
import com.orioninc.ProjectRestaurants.DTO.expire.ExpireResponseDTO;
import com.orioninc.ProjectRestaurants.model.Expire;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductExpireService {

    List<ExpireResponseDTO> getAllProductExpires();
    ExpireResponseDTO getProductExpireById(Long id);
    Expire saveProductExpire(Expire expire);
    Expire updateProductExpire(ExpireRequestDTO expireRequestDTO);
    void deleteProductExpire(long id);

}

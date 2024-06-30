package com.orioninc.ProjectRestaurants.DTO.expire;

import com.orioninc.ProjectRestaurants.model.Expire;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class ExpireResponseDTOMapper implements Function<Expire, ExpireResponseDTO> {
    @Override
    public ExpireResponseDTO apply(Expire expire) {
        return new ExpireResponseDTO(
                expire.getId(),
                expire.getExpireDate(),
                expire.getBatchQuantity(),
                expire.getProduct().getId());
    }
}

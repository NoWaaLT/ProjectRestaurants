package com.orioninc.ProjectRestaurants.DTO.expire;

import java.util.Date;

public record ExpireResponseDTO(Long id,
                                Date expireDate,
                                Float batchQuantity,
                                Long productId) {
}

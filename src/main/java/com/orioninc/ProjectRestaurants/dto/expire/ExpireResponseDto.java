package com.orioninc.ProjectRestaurants.dto.expire;

import java.util.Date;

public record ExpireResponseDto(Long id,
                                Date expireDate,
                                Float batchQuantity,
                                Long productId,
                                Boolean removedProduct) {
}

package com.orioninc.ProjectRestaurants.dto.warehouse;

import java.util.Date;

public record WarehouseDto(Integer id,
                           String name,
                           String type,
                           String quality,
                           Integer quantity,
                           Float price,
                           Date productionDate,
                           Integer lifetimeDays) {
}



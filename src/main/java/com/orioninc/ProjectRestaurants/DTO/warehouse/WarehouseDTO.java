package com.orioninc.ProjectRestaurants.DTO.warehouse;

import java.util.Date;

public record WarehouseDTO(Integer id,
                           String name,
                           String type,
                           String quality,
                           Integer quantity,
                           Float price,
                           Date productionDate,
                           Integer lifetimeDays) {
}



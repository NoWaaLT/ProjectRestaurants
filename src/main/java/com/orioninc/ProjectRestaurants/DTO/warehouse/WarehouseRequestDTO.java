package com.orioninc.ProjectRestaurants.DTO.warehouse;

import com.orioninc.ProjectRestaurants.model.warehouse.WarehouseProduct;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class WarehouseRequestDTO implements Function<WarehouseDTO, WarehouseProduct> {
    @Override
    public WarehouseProduct apply(WarehouseDTO warehouseDTO) {
        WarehouseProduct warehouseProduct = new WarehouseProduct();
        warehouseProduct.setName(warehouseDTO.name());
        warehouseProduct.setType(warehouseDTO.type());
        warehouseProduct.setQuality(warehouseDTO.quality());
        warehouseProduct.setQuantity(warehouseDTO.quantity());
        warehouseProduct.setPrice(warehouseDTO.price());
        warehouseProduct.setProductionDate((Date) warehouseDTO.productionDate());
        warehouseProduct.setLifetimeDays(warehouseDTO.lifetimeDays());

        return warehouseProduct;
    }
}

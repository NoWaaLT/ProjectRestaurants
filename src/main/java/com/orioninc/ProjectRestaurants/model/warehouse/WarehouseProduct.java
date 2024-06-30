package com.orioninc.ProjectRestaurants.model.warehouse;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class WarehouseProduct {

    private Integer id;
    private String name;
    private String type;
    private String quality;
    private Integer quantity;
    private Float price;
    private Date productionDate;
    private Integer lifetimeDays;

}
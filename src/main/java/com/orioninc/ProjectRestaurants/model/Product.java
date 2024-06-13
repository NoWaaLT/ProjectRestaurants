package com.orioninc.ProjectRestaurants.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

    private Long id;
    private String productName;
    private float productPrice;
    private int productBalance;
    private Restaurant restaurantType;

}

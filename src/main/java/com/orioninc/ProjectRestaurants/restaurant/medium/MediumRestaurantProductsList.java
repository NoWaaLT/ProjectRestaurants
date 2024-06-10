package com.orioninc.ProjectRestaurants.restaurant.medium;

import jakarta.persistence.*;

@Table(name = "products_")
@Entity
public class MediumRestaurantProductsList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private Float productPrice;

    @Column(name = "product_quantity")
    private Integer productQuantity;

}
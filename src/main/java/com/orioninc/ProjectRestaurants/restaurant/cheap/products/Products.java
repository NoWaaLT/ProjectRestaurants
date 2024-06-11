package com.orioninc.ProjectRestaurants.restaurant.cheap.products;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_price")
    private float productPrice;
    @Column(name = "product_balance")
    private int productBalance;
}

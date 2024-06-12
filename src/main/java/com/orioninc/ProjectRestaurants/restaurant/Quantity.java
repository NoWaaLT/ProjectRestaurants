package com.orioninc.ProjectRestaurants.restaurant;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class Quantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "recipe_id")
    private int recipeId;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "product_quantity")
    private int quantity;
}

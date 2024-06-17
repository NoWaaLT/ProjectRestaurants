package com.orioninc.ProjectRestaurants.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "recipe_quantity")
public class RecipeQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "fk_recipe_id", referencedColumnName = "id")
    private Recipe recipe;

    @Column(name = "quantity")
    private Integer quantity;
}

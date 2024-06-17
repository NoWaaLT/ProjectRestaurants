package com.orioninc.ProjectRestaurants.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "products")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private Float productPrice;

    @Column(name = "product_balance")
    private Integer productBalance;

    @ManyToOne
    @JoinColumn(name = "fk_restaurant_id", referencedColumnName = "id")
    @JsonBackReference
    private Restaurant restaurant;

//    @OneToMany(mappedBy = "product")
//    private Set<RecipeQuantity> recipeQuantities;

}
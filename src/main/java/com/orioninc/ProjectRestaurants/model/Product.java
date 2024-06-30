package com.orioninc.ProjectRestaurants.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.orioninc.ProjectRestaurants.repository.ProductRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;

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
    private Float productBalance;

    @ManyToOne
    @JoinColumn(name = "fk_restaurant_id", referencedColumnName = "id")
    @JsonBackReference
    private Restaurant restaurant;

    @Column(name = "minimum_balance")
    private Float productMinimumBalance;

    @Column(name = "product_expirable")
    private Integer productExpirable;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Expire> expireSet;


//    @OneToMany(mappedBy = "productId")
//    private Set<RecipeQuantity> recipeQuantities;

}
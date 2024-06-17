package com.orioninc.ProjectRestaurants.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recipe_name")
    private String recipeName;

    @Column(name = "recipe_price")
    private Float recipePrice;

    @ManyToOne
    @JoinColumn(name = "fk_menu_id", referencedColumnName = "id")
    private Menu menu;

    @OneToMany(mappedBy = "recipe")
    private Set<RecipeQuantity> recipeQuantities;

}

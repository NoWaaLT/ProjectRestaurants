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
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dish_name")
    private String dishName;

    @Column(name = "dish_price")
    private Float dishPrice;

    @ManyToOne
    @JoinColumn(name = "fk_menu_id", referencedColumnName = "id")
    private Menu menu;

    @OneToMany(mappedBy = "dish")
    private Set<Recipe> recipeQuantities;

}

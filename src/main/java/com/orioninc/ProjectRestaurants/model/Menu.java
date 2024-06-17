package com.orioninc.ProjectRestaurants.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "menus")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu_name")
    private String menuName;

    @ManyToOne
    @JoinColumn(name = "fk_restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menu")
    private List<Recipe> recipeList;

}

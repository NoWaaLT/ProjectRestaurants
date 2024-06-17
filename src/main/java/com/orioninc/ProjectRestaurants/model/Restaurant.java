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
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Enumerated(EnumType.STRING)
    @Column(name = "restaurant_type")
    private RestaurantType restaurantType;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orderList;

    @OneToMany(mappedBy = "restaurant")
    private List<Product> productList;

    @OneToMany(mappedBy = "restaurant")
    private List<Menu> menuList;

//    public Restaurant(Long id) {
//        int idR = Math.toIntExact(id);
//
//        switch (idR) {
//            case 1 -> {
//                this.id = id;
//                this.restaurantName = "Santino Moreno";
//                this.restaurantType = CHEAP;
//                this.orderList = null;
//                this.productList = null;
//                this.menuList = null;
//            }
//
//            case 2 -> {
//                this.id = id;
//                this.restaurantName = "Pachamama Dinner Club";
//                this.restaurantType = MEDIUM;
//                this.orderList = null;
//                this.productList = null;
//                this.menuList = null;
//            }
//
//            case 3 -> {
//                this.id = id;
//                this.restaurantName = "Amandus";
//                this.restaurantType = ELITE;
//                this.orderList = null;
//                this.productList = null;
//                this.menuList = null;
//            }
//        }
//    }

}
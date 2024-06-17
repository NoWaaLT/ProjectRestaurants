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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_name")
    private String orderName;

    @ManyToOne
    @JoinColumn(name = "fk_restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", referencedColumnName = "id")
    private User user;
}

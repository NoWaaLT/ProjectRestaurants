package com.orioninc.ProjectRestaurants.order;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_name")
    private String order_name;

    @Column(name = "order_owner")
    private String order_owner;

    @Column(name = "order_created")
    private LocalDateTime order_created;

    public Order() {
    }

    public Order(String order_name, String order_owner, LocalDateTime order_created) {
        this.order_name = order_name;
        this.order_owner = order_owner;
        this.order_created = order_created;
    }
}

package com.orioninc.ProjectRestaurants.order;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "order_records")
@Entity
public class OrderRecords extends Order {

    private Long id;

    private Integer count;
}

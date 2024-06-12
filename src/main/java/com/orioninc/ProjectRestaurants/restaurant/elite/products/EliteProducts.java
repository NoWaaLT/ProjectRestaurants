package com.orioninc.ProjectRestaurants.restaurant.elite.products;

import com.orioninc.ProjectRestaurants.restaurant.Products;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "products_elite")
@AllArgsConstructor
public class EliteProducts extends Products {

}

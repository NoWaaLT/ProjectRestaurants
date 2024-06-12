package com.orioninc.ProjectRestaurants.restaurant.medium.products;

import com.orioninc.ProjectRestaurants.restaurant.Products;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "products_medium")
@AllArgsConstructor
public class MediumProducts extends Products {

}

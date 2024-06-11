package com.orioninc.ProjectRestaurants.restaurant.cheap.products;

import com.orioninc.ProjectRestaurants.restaurant.Products;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products_cheap")
@AllArgsConstructor
public class CheapProducts extends Products {

}

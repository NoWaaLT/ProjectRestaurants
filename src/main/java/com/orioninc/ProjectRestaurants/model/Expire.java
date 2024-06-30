package com.orioninc.ProjectRestaurants.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "products_expire")
public class Expire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expire")
    private Date expireDate;

    @Column(name = "batch_quantity")
    private Float batchQuantity;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "fk_product_id", referencedColumnName = "id")
    private Product product;

}

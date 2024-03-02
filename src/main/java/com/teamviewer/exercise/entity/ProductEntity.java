package com.teamviewer.exercise.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_gen")
    @SequenceGenerator(name = "products_gen", sequenceName = "products_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

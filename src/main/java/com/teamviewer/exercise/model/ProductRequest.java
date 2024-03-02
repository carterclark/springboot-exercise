package com.teamviewer.exercise.model;

import com.teamviewer.exercise.entity.ProductEntity;

public class ProductRequest {

    private String name;
    private Double price;

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

    public ProductEntity toProductEntity(){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(this.name);
        productEntity.setPrice(this.price);
        return productEntity;
    }
}

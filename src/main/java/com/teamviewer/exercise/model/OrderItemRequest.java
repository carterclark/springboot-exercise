package com.teamviewer.exercise.model;


import com.teamviewer.exercise.entity.OrderItemEntity;
import com.teamviewer.exercise.entity.ProductEntity;

public class OrderItemRequest {

    private int quantity;
    private Long productId;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public OrderItemEntity toOrderItemEntity(ProductEntity productEntity){
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setQuantity(this.quantity);
        orderItemEntity.setProductEntity(productEntity);

        return orderItemEntity;
    }

}

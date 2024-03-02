package com.teamviewer.exercise.model;


import com.teamviewer.exercise.entity.OrderEntity;
import com.teamviewer.exercise.entity.OrderItemEntity;
import com.teamviewer.exercise.entity.ProductEntity;

public class OrderRequest {

    private String customerName;
    private Long orderItemId;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public OrderEntity toOrderEntity(OrderItemEntity orderItemEntity){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomerName(this.customerName);
        orderEntity.setOrderItemEntity(orderItemEntity);

        return orderEntity;
    }

}

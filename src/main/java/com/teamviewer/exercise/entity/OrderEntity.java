package com.teamviewer.exercise.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_gen")
    @SequenceGenerator(name = "orders_gen", sequenceName = "orders_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @ManyToOne
    @JoinColumn(name = "order_item_id", referencedColumnName = "id")
    private OrderItemEntity orderItemEntity;
    public OrderEntity(){}

    public OrderEntity(Long id, String customerName, OrderItemEntity orderItemEntity) {
        this.id = id;
        this.customerName= customerName;
        this.orderItemEntity = orderItemEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public OrderItemEntity getOrderItemEntity() {
        return orderItemEntity;
    }

    public void setOrderItemEntity(OrderItemEntity orderItemEntity) {
        this.orderItemEntity = orderItemEntity;
    }

}

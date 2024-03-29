package com.teamviewer.exercise.testUtils;

import com.teamviewer.exercise.entity.OrderEntity;
import com.teamviewer.exercise.entity.OrderItemEntity;
import com.teamviewer.exercise.entity.ProductEntity;
import com.teamviewer.exercise.model.OrderItemRequest;
import com.teamviewer.exercise.model.OrderRequest;
import com.teamviewer.exercise.model.ProductRequest;

import java.util.ArrayList;
import java.util.List;

public class util {
    public static List<ProductEntity> getProductEntities() {
        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(new ProductEntity(1L, "name1", 10.0));
        return productEntities;
    }

    public static List<OrderItemEntity> getOrderItemEntities(){
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        for (int i = 0; i < getProductEntities().size(); i++) {
            orderItemEntities.add(
                    new OrderItemEntity(Integer.toUnsignedLong(i), 1, getProductEntities().get(i))
            );

        }
        return orderItemEntities;
    }

    public static List<OrderEntity> getOrderEntities() {
        List<OrderEntity> orderEntities = new ArrayList<>();

        for (int i = 0; i < getOrderItemEntities().size(); i++) {
            orderEntities.add(
                    new OrderEntity(Integer.toUnsignedLong(i), "testCustomer" + i, getOrderItemEntities().get(i))
            );
        }
        return orderEntities;
    }

    public static ProductRequest getProductRequest() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("testName");
        productRequest.setPrice(12.34);
        return productRequest;
    }

    public static OrderItemRequest getOrderItemRequest() {
        OrderItemRequest orderItemRequest = new OrderItemRequest();
        orderItemRequest.setQuantity(5);
        orderItemRequest.setProductId(1L);
        return orderItemRequest;
    }

    public static OrderRequest getOrderRequest() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderItemId(1L);
        orderRequest.setCustomerName("testCustomer");
        return orderRequest;
    }


}

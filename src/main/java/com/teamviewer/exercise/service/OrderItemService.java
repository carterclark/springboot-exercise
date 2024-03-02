package com.teamviewer.exercise.service;

import com.teamviewer.exercise.entity.OrderItemEntity;
import com.teamviewer.exercise.entity.ProductEntity;
import com.teamviewer.exercise.model.OrderItemRequest;
import com.teamviewer.exercise.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ProductService productService;

    public List<OrderItemEntity> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItemEntity getOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    public OrderItemEntity createOrderItem(OrderItemRequest orderItemRequest) {
        ProductEntity productEntity = productService.getProductById(orderItemRequest.getProductId());
        return orderItemRepository.save(orderItemRequest.toOrderItemEntity(productEntity));
    }

    public OrderItemEntity updateOrderItem(Long id, OrderItemRequest orderItemRequest) {
        OrderItemEntity existingOrderItemEntity = getOrderItemById(id);
        if(existingOrderItemEntity == null) return null;

        ProductEntity productEntity = productService.getProductById(orderItemRequest.getProductId());
        existingOrderItemEntity.setProductEntity(productEntity);
        existingOrderItemEntity.setQuantity(orderItemRequest.getQuantity());

        return orderItemRepository.save(existingOrderItemEntity);
    }

    public boolean deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
        return true;
    }
}

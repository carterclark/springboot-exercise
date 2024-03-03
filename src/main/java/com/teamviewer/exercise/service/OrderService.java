package com.teamviewer.exercise.service;

import com.teamviewer.exercise.entity.OrderEntity;
import com.teamviewer.exercise.entity.OrderItemEntity;
import com.teamviewer.exercise.model.OrderRequest;
import com.teamviewer.exercise.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemService orderItemService;

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public OrderEntity createOrder(OrderRequest orderRequest) {
        OrderItemEntity orderItemEntity = orderItemService.getOrderItemById(orderRequest.getOrderItemId());
        if(orderItemEntity == null) return null;
        return orderRepository.save(orderRequest.toOrderEntity(orderItemEntity));
    }

    public OrderEntity updateOrder(Long id, OrderRequest orderRequest) {
        OrderEntity existingOrderEntity  = getOrderById(id);
        if(existingOrderEntity == null) return null;

        OrderItemEntity orderItemEntity = orderItemService.getOrderItemById(orderRequest.getOrderItemId());
        existingOrderEntity.setCustomerName(orderRequest.getCustomerName());
        existingOrderEntity.setOrderItemEntity(orderItemEntity);
        return orderRepository.save(existingOrderEntity);
    }

    public boolean deleteOrder(Long id) {
        OrderEntity existingOrder = getOrderById(id);
        if(existingOrder == null) return false;
        orderRepository.deleteById(id);
        return true;
    }


}

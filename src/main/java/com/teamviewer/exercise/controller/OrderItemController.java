package com.teamviewer.exercise.controller;

import com.teamviewer.exercise.entity.OrderItemEntity;
import com.teamviewer.exercise.model.OrderItemRequest;
import com.teamviewer.exercise.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;


    @GetMapping
    public ResponseEntity<List<OrderItemEntity>> getAllOrderItems() {
        List<OrderItemEntity> orderItemEntities = orderItemService.getAllOrderItems();
        return new ResponseEntity<>(orderItemEntities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemEntity> getOrderItemById(@PathVariable Long id) {
        OrderItemEntity orderItemEntity = orderItemService.getOrderItemById(id);
        if (orderItemEntity != null) {
            return new ResponseEntity<>(orderItemEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<OrderItemEntity> createOrderItem(@RequestBody OrderItemRequest orderItemRequest) {
        OrderItemEntity createdOrderItemEntity = orderItemService.createOrderItem(orderItemRequest);
        return new ResponseEntity<>(createdOrderItemEntity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemEntity> updateOrderItem(
            @PathVariable Long id,
            @RequestBody OrderItemRequest orderItemRequest
    ) {
        OrderItemEntity updatedOrderItemEntity = orderItemService.updateOrderItem(id, orderItemRequest);
        if (updatedOrderItemEntity != null) {
            return new ResponseEntity<>(updatedOrderItemEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        boolean deleted = orderItemService.deleteOrderItem(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

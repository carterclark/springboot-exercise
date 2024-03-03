package com.teamviewer.exercise.controller;

import com.teamviewer.exercise.entity.OrderEntity;
import com.teamviewer.exercise.model.OrderRequest;
import com.teamviewer.exercise.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        List<OrderEntity> orderEntities = orderService.getAllOrders();
        return new ResponseEntity<>(orderEntities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable Long id) {
        OrderEntity orderEntity = orderService.getOrderById(id);
        if (orderEntity != null) {
            return new ResponseEntity<>(orderEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderRequest orderRequest) {
        OrderEntity createdOrderEntity = orderService.createOrder(orderRequest);
        if(createdOrderEntity == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(createdOrderEntity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderEntity> updateOrder(
            @PathVariable Long id,
            @RequestBody OrderRequest orderRequest
    ) {
        OrderEntity updatedOrderEntity = orderService.updateOrder(id, orderRequest);
        if (updatedOrderEntity != null) {
            return new ResponseEntity<>(updatedOrderEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        boolean deleted = orderService.deleteOrder(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

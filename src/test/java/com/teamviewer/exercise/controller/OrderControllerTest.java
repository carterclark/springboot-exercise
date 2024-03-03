package com.teamviewer.exercise.controller;

import com.teamviewer.exercise.entity.OrderEntity;
import com.teamviewer.exercise.entity.ProductEntity;
import com.teamviewer.exercise.model.OrderRequest;
import com.teamviewer.exercise.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.teamviewer.exercise.testUtils.util.getOrderEntities;
import static com.teamviewer.exercise.testUtils.util.getOrderRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Test
    void getAllOrderItems() {
        ResponseEntity<List<OrderEntity>> expected = new ResponseEntity<>(getOrderEntities(), HttpStatus.OK);

        when(orderService.getAllOrders()).thenReturn(getOrderEntities());
        ResponseEntity<List<OrderEntity>> actual = orderController.getAllOrders();

        assertEquals(
                Objects.requireNonNull(expected.getBody()).get(0).getId(),
                Objects.requireNonNull(actual.getBody()).get(0).getId()
        );
        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    @Test
    void getOrderById() {
        ResponseEntity<OrderEntity> expected = new ResponseEntity<>(getOrderEntities().get(0), HttpStatus.OK);

        when(orderService.getOrderById(1L)).thenReturn(getOrderEntities().get(0));
        ResponseEntity<OrderEntity> actual = orderController.getOrderById(1L);

        assertEquals(
                Objects.requireNonNull(expected.getBody()).getId(),
                Objects.requireNonNull(actual.getBody()).getId()
        );
        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    @Test
    void createOrder() {
        ResponseEntity<OrderEntity> expected = new ResponseEntity<>(getOrderEntities().get(0), HttpStatus.CREATED);

        when(orderService.createOrder(any()))
                .thenReturn(getOrderEntities().get(0));
        ResponseEntity<OrderEntity> actual = orderController.createOrder(getOrderRequest());

        assertEquals(
                Objects.requireNonNull(expected.getBody()).getId(),
                Objects.requireNonNull(actual.getBody()).getId()
        );

        assertEquals(expected.getStatusCode(), actual.getStatusCode());

    }

    @Test
    void createOrderNullResponse() {
        ResponseEntity<OrderEntity> expected = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        when(orderService.createOrder(any()))
                .thenReturn(null);
        ResponseEntity<OrderEntity> actual = orderController.createOrder(getOrderRequest());

        assertEquals(
                expected.getBody(),
                actual.getBody()
        );
        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    @Test
    void updateOrder() {
        ResponseEntity<OrderEntity> expected = new ResponseEntity<>(getOrderEntities().get(0), HttpStatus.OK);

        when(orderService.updateOrder(any(), any()))
                .thenReturn(getOrderEntities().get(0));

        ResponseEntity<OrderEntity> actual = orderController.updateOrder(1L, getOrderRequest());

        assertEquals(
                Objects.requireNonNull(expected.getBody()).getId(),
                Objects.requireNonNull(actual.getBody()).getId()
        );
        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    @Test
    void updateOrderNotFound() {
        ResponseEntity<OrderEntity> expected = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        when(orderService.updateOrder(any(), any()))
                .thenReturn(null);

        ResponseEntity<OrderEntity> actual = orderController.updateOrder(1L, getOrderRequest());

        assertEquals(
                expected.getBody(),
                actual.getBody()
        );
        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    @Test
    void deleteOrder() {
        ResponseEntity<OrderEntity> expected = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        when(orderService.deleteOrder(1L)).thenReturn(true);
        ResponseEntity<Void> actual = orderController.deleteOrder(1L);

        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    @Test
    void deleteOrderNotFound() {
        ResponseEntity<OrderEntity> expected = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        when(orderService.deleteOrder(1L)).thenReturn(false);
        ResponseEntity<Void> actual = orderController.deleteOrder(1L);

        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

}

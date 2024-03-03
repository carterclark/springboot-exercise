package com.teamviewer.exercise.controller;

import com.teamviewer.exercise.entity.OrderItemEntity;
import com.teamviewer.exercise.model.OrderItemRequest;
import com.teamviewer.exercise.service.OrderItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static com.teamviewer.exercise.testUtils.util.getOrderItemEntities;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderItemControllerTest {

    @Mock
    private OrderItemService orderItemService;

    private OrderItemController orderItemController;

    @BeforeEach
    public void setUp() {
        orderItemController = new OrderItemController();
        orderItemController.orderItemService = orderItemService;
    }

    @Test void getAllOrderItems() {
        ResponseEntity<List<OrderItemEntity>> expected = new ResponseEntity<>(getOrderItemEntities(), HttpStatus.OK);

        when(orderItemService.getAllOrderItems()).thenReturn(getOrderItemEntities());
        ResponseEntity<List<OrderItemEntity>> actual = orderItemController.getAllOrderItems();

        assertEquals(
                Objects.requireNonNull(expected.getBody()).get(0).getId(),
                Objects.requireNonNull(actual.getBody()).get(0).getId()
        );
        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    @Test void getOrderItemById() {
        ResponseEntity<OrderItemEntity> expected = new ResponseEntity<>(getOrderItemEntities().get(0), HttpStatus.OK);

        when(orderItemService.getOrderItemById(1L)).thenReturn(getOrderItemEntities().get(0));
        ResponseEntity<OrderItemEntity> actual = orderItemController.getOrderItemById(1L);

        assertEquals(
                Objects.requireNonNull(expected.getBody()).getId(),
                Objects.requireNonNull(actual.getBody()).getId()
        );
        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    @Test void createOrderItem() {
        ResponseEntity<OrderItemEntity> expected = new ResponseEntity<>(getOrderItemEntities().get(0), HttpStatus.CREATED);

        when(orderItemService.createOrderItem(any()))
            .thenReturn(getOrderItemEntities().get(0));
        ResponseEntity<OrderItemEntity> actual = orderItemController.createOrderItem(getOrderItemRequest());

        assertEquals(
                Objects.requireNonNull(expected.getBody()).getId(),
                Objects.requireNonNull(actual.getBody()).getId()
        );

        assertEquals(expected.getStatusCode(), actual.getStatusCode());

    }

    @Test void createOrderItemNullResponse() {
        ResponseEntity<OrderItemEntity> expected = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        when(orderItemService.createOrderItem(any()))
            .thenReturn(null);
        ResponseEntity<OrderItemEntity> actual = orderItemController.createOrderItem(getOrderItemRequest());

        assertEquals(
                expected.getBody(),
                actual.getBody()
        );
        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    @Test void updateOrderItem() {
        ResponseEntity<OrderItemEntity> expected = new ResponseEntity<>(getOrderItemEntities().get(0), HttpStatus.OK);

        when(orderItemService.updateOrderItem(any(), any()))
            .thenReturn(getOrderItemEntities().get(0));

        ResponseEntity<OrderItemEntity> actual = orderItemController.updateOrderItem(1L, getOrderItemRequest());

        assertEquals(
                Objects.requireNonNull(expected.getBody()).getId(),
                Objects.requireNonNull(actual.getBody()).getId()
        );
        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    @Test void updateOrderItemNotFound() {
        ResponseEntity<OrderItemEntity> expected = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        when(orderItemService.updateOrderItem(any(), any()))
            .thenReturn(null);

        ResponseEntity<OrderItemEntity> actual = orderItemController.updateOrderItem(1L, getOrderItemRequest());

        assertEquals(
                expected.getBody(),
                actual.getBody()
        );
        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    @Test void deleteOrderItem() {
        ResponseEntity<OrderItemEntity> expected = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        when(orderItemService.deleteOrderItem(1L)).thenReturn(true);
        ResponseEntity<Void> actual = orderItemController.deleteOrderItem(1L);

        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    @Test void deleteOrderItemNotFound() {
        ResponseEntity<OrderItemEntity> expected = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        when(orderItemService.deleteOrderItem(1L)).thenReturn(false);
        ResponseEntity<Void> actual = orderItemController.deleteOrderItem(1L);

        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    private OrderItemRequest getOrderItemRequest() {
        OrderItemRequest orderItemRequest = new OrderItemRequest();
        orderItemRequest.setQuantity(5);
        orderItemRequest.setProductId(1L);
        return orderItemRequest;
    }
}

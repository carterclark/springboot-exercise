package com.teamviewer.exercise.service;

import com.teamviewer.exercise.entity.OrderEntity;
import com.teamviewer.exercise.model.OrderRequest;
import com.teamviewer.exercise.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.teamviewer.exercise.testUtils.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderItemRepository;

    @Mock
    private OrderItemService orderItemService;

    @InjectMocks
    private OrderService orderService;

    @Test void getAllOrders() {
        List<OrderEntity> expected = getOrderEntities();
        when(orderItemRepository.findAll()).thenReturn(getOrderEntities());
        List<OrderEntity> actual = orderService.getAllOrders();

        verify(orderItemRepository, times(1)).findAll();
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
    }

    @Test void getOrderById() {
        OrderEntity expected = getOrderEntities().get(0);
        when(orderItemRepository.findById(1L)).thenReturn(Optional.ofNullable(getOrderEntities().get(0)));
        OrderEntity actual = orderService.getOrderById(1L);

        verify(orderItemRepository, times(1)).findById(1L);
        assertEquals(expected.getId(), actual.getId());
    }

    @Test void createOrder() {
        OrderEntity expected = getOrderEntities().get(0);
        when(orderItemRepository.save(any())).thenReturn(getOrderEntities().get(0));
        when(orderItemService.getOrderItemById(1L)).thenReturn(getOrderItemEntities().get(0));
        OrderEntity actual = orderService.createOrder(getOrderRequest());

        verify(orderItemService, times(1)).getOrderItemById(1L);
        verify(orderItemRepository, times(1)).save(any());
        assertEquals(expected.getId(), actual.getId());
    }

    @Test void createOrderNull() {
        when(orderItemService.getOrderItemById(1L)).thenReturn(null);
        OrderEntity actual = orderService.createOrder(getOrderRequest());

        verify(orderItemService, times(1)).getOrderItemById(1L);
        verify(orderItemRepository, times(0)).save(any());
        assertNull(actual);
    }

    @Test void updateOrder() {
        String updatedName = "updatedCustomer";
        OrderEntity expected = getOrderEntities().get(0);
        expected.setCustomerName(updatedName);

        OrderRequest updateRequest = getOrderRequest();
        updateRequest.setCustomerName(updatedName);

        when(orderItemRepository.findById(1L)).thenReturn(Optional.ofNullable(getOrderEntities().get(0)));
        when(orderItemRepository.save(any())).thenReturn(expected);

        OrderEntity actual = orderService.updateOrder(1L, updateRequest);

        verify(orderItemRepository, times(1)).findById(1L);
        verify(orderItemRepository, times(1)).save(any());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(updatedName, actual.getCustomerName());
    }

    @Test void updateOrderNull() {
        String updatedName = "updatedCustomer";

        OrderRequest updateRequest = getOrderRequest();
        updateRequest.setCustomerName(updatedName);
        when(orderItemRepository.findById(1L)).thenReturn(Optional.empty());
        OrderEntity actual = orderService.updateOrder(1L, updateRequest);

        verify(orderItemRepository, times(1)).findById(1L);
        verify(orderItemRepository, times(0)).save(any());
        assertNull(actual);
    }

    @Test void deleteOrder() {
        when(orderItemRepository.findById(1L)).thenReturn(Optional.ofNullable(getOrderEntities().get(0)));
        boolean actual = orderService.deleteOrder(1L);

        verify(orderItemRepository, times(1)).deleteById(1L);

        assertTrue(actual);
    }

    @Test void deleteOrderNotFound() {
        when(orderItemRepository.findById(1L)).thenReturn(Optional.empty());
        boolean actual = orderService.deleteOrder(1L);

        verify(orderItemRepository, times(0)).deleteById(1L);
        assertFalse(actual);
    }
}

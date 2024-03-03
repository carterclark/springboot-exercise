package com.teamviewer.exercise.service;

import com.teamviewer.exercise.entity.OrderItemEntity;
import com.teamviewer.exercise.model.OrderItemRequest;
import com.teamviewer.exercise.repository.OrderItemRepository;
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
class OrderItemServiceTest {

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private OrderItemService orderItemService;

    @Test void getAllOrderItems() {
        List<OrderItemEntity> expected = getOrderItemEntities();
        when(orderItemRepository.findAll()).thenReturn(getOrderItemEntities());
        List<OrderItemEntity> actual = orderItemService.getAllOrderItems();

        verify(orderItemRepository, times(1)).findAll();
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
    }

    @Test void getOrderItemById() {
        OrderItemEntity expected = getOrderItemEntities().get(0);
        when(orderItemRepository.findById(1L)).thenReturn(Optional.ofNullable(getOrderItemEntities().get(0)));
        OrderItemEntity actual = orderItemService.getOrderItemById(1L);

        verify(orderItemRepository, times(1)).findById(1L);
        assertEquals(expected.getId(), actual.getId());
    }

    @Test void createOrderItem() {
        OrderItemEntity expected = getOrderItemEntities().get(0);
        when(orderItemRepository.save(any())).thenReturn(getOrderItemEntities().get(0));
        when(productService.getProductById(1L)).thenReturn(getProductEntities().get(0));
        OrderItemEntity actual = orderItemService.createOrderItem(getOrderItemRequest());

        verify(productService, times(1)).getProductById(1L);
        verify(orderItemRepository, times(1)).save(any());
        assertEquals(expected.getId(), actual.getId());
    }

    @Test void createOrderItemNull() {
        OrderItemEntity expected = getOrderItemEntities().get(0);
        when(productService.getProductById(1L)).thenReturn(null);
        OrderItemEntity actual = orderItemService.createOrderItem(getOrderItemRequest());

        verify(productService, times(1)).getProductById(1L);
        verify(orderItemRepository, times(0)).save(any());
        assertNull(actual);
    }

    @Test void updateOrderItem() {
        int updatedQuantity = 35;
        OrderItemEntity expected = getOrderItemEntities().get(0);
        expected.setQuantity(updatedQuantity);

        OrderItemRequest updateRequest = getOrderItemRequest();
        updateRequest.setQuantity(updatedQuantity);

        when(orderItemRepository.findById(1L)).thenReturn(Optional.ofNullable(getOrderItemEntities().get(0)));
        when(orderItemRepository.save(any())).thenReturn(expected);

        OrderItemEntity actual = orderItemService.updateOrderItem(1L, updateRequest);

        verify(orderItemRepository, times(1)).findById(1L);
        verify(orderItemRepository, times(1)).save(any());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(updatedQuantity, actual.getQuantity());
    }

    @Test void updateOrderItemNull() {
        int updatedQuantity = 35;

        OrderItemRequest updateRequest = getOrderItemRequest();
        updateRequest.setQuantity(updatedQuantity);
        when(orderItemRepository.findById(1L)).thenReturn(Optional.empty());
        OrderItemEntity actual = orderItemService.updateOrderItem(1L, updateRequest);

        verify(orderItemRepository, times(1)).findById(1L);
        verify(orderItemRepository, times(0)).save(any());
        assertNull(actual);
    }

    @Test void deleteOrderItem() {
        when(orderItemRepository.findById(1L)).thenReturn(Optional.ofNullable(getOrderItemEntities().get(0)));
        boolean actual = orderItemService.deleteOrderItem(1L);

        verify(orderItemRepository, times(1)).deleteById(1L);

        assertTrue(actual);
    }

    @Test void deleteOrderItemNotFound() {
        when(orderItemRepository.findById(1L)).thenReturn(Optional.empty());
        boolean actual = orderItemService.deleteOrderItem(1L);

        verify(orderItemRepository, times(0)).deleteById(1L);
        assertFalse(actual);
    }
}

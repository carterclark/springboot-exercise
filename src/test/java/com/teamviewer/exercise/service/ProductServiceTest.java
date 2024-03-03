package com.teamviewer.exercise.service;
import com.teamviewer.exercise.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp(){

    }

    @Test void getAllOrderItems() {
    }

    @Test void getOrderItemById() {
    }

    @Test void createOrderItem() {
    }

    @Test void updateOrderItem() {
    }

    @Test void deleteOrderItem() {
    }
}

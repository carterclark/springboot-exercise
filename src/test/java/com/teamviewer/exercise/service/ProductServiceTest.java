package com.teamviewer.exercise.service;

import com.teamviewer.exercise.entity.ProductEntity;
import com.teamviewer.exercise.model.ProductRequest;
import com.teamviewer.exercise.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.teamviewer.exercise.testUtils.util.getProductEntities;
import static com.teamviewer.exercise.testUtils.util.getProductRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test void getAllProducts() {
        List<ProductEntity> expected = getProductEntities();
        when(productRepository.findAll()).thenReturn(getProductEntities());
        List<ProductEntity> actual = productService.getAllProducts();

        verify(productRepository, times(1)).findAll();
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
    }

    @Test void getProductById() {
        ProductEntity expected = getProductEntities().get(0);
        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(getProductEntities().get(0)));
        ProductEntity actual = productService.getProductById(1L);

        verify(productRepository, times(1)).findById(1L);
        assertEquals(expected.getId(), actual.getId());
    }

    @Test void createProduct() {
        ProductEntity expected = getProductEntities().get(0);
        when(productRepository.save(any())).thenReturn(getProductEntities().get(0));
        ProductEntity actual = productService.createProduct(getProductRequest());

        verify(productRepository, times(1)).save(any());
        assertEquals(expected.getId(), actual.getId());
    }

    @Test void updateProduct() {
        String updatedName = "updatedName";
        ProductEntity expected = getProductEntities().get(0);
        expected.setName(updatedName);

        ProductRequest updateRequest = getProductRequest();
        updateRequest.setName(updatedName);

        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(getProductEntities().get(0)));
        when(productRepository.save(any())).thenReturn(expected);

        ProductEntity actual = productService.updateProduct(1L, updateRequest);

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(updatedName, actual.getName());
    }

    @Test void updateProductNull() {
        String updatedName = "updatedName";

        ProductRequest updateRequest = getProductRequest();
        updateRequest.setName(updatedName);
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        ProductEntity actual = productService.updateProduct(1L, updateRequest);

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(0)).save(any());
        assertNull(actual);
    }

    @Test void deleteProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(getProductEntities().get(0)));
        boolean actual = productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);

        assertTrue(actual);
    }

    @Test void deleteProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        boolean actual = productService.deleteProduct(1L);

        verify(productRepository, times(0)).deleteById(1L);
        assertFalse(actual);
    }
}

package com.teamviewer.exercise.controller;

import com.teamviewer.exercise.entity.ProductEntity;
import com.teamviewer.exercise.model.ProductRequest;
import com.teamviewer.exercise.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static com.teamviewer.exercise.testUtils.util.getProductEntities;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        productController = new ProductController();
        productController.productService = productService;
    }

    @Test void getAllProducts() {
        ResponseEntity<List<ProductEntity>> expected = new ResponseEntity<>(getProductEntities(), HttpStatus.OK);

        when(productService.getAllProducts()).thenReturn(getProductEntities());
        ResponseEntity<List<ProductEntity>> actual = productController.getAllProducts();

        assertEquals(
                Objects.requireNonNull(expected.getBody()).get(0).getId(),
                Objects.requireNonNull(actual.getBody()).get(0).getId()
        );
        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    @Test void getProductById() {
        ResponseEntity<ProductEntity> expected = new ResponseEntity<>(getProductEntities().get(0), HttpStatus.OK);

        when(productService.getProductById(1L)).thenReturn(getProductEntities().get(0));
        ResponseEntity<ProductEntity> actual = productController.getProductById(1L);

        assertEquals(
                Objects.requireNonNull(expected.getBody()).getId(),
                Objects.requireNonNull(actual.getBody()).getId()
        );
        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    @Test void createProduct() {
        ResponseEntity<ProductEntity> expected = new ResponseEntity<>(getProductEntities().get(0), HttpStatus.CREATED);

        when(productService.createProduct(any()))
            .thenReturn(getProductEntities().get(0));
        ResponseEntity<ProductEntity> actual = productController.createProduct(getProductRequest());

        assertEquals(
                Objects.requireNonNull(expected.getBody()).getId(),
                Objects.requireNonNull(actual.getBody()).getId()
        );

        assertEquals(expected.getStatusCode(), actual.getStatusCode());

    }

    @Test void createProductNullResponse() {
        ResponseEntity<ProductEntity> expected = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        when(productService.createProduct(any()))
            .thenReturn(null);
        ResponseEntity<ProductEntity> actual = productController.createProduct(getProductRequest());

        assertEquals(
                expected.getBody(),
                actual.getBody()
        );
        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    @Test void updateProduct() {
        ResponseEntity<ProductEntity> expected = new ResponseEntity<>(getProductEntities().get(0), HttpStatus.OK);

        when(productService.updateProduct(any(), any()))
            .thenReturn(getProductEntities().get(0));

        ResponseEntity<ProductEntity> actual = productController.updateProduct(1L, getProductRequest());

        assertEquals(
                Objects.requireNonNull(expected.getBody()).getId(),
                Objects.requireNonNull(actual.getBody()).getId()
        );
        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    @Test void updateProductNotFound() {
        ResponseEntity<ProductEntity> expected = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        when(productService.updateProduct(any(), any()))
            .thenReturn(null);

        ResponseEntity<ProductEntity> actual = productController.updateProduct(1L, getProductRequest());

        assertEquals(
                expected.getBody(),
                actual.getBody()
        );
        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    @Test void deleteProduct() {
        ResponseEntity<ProductEntity> expected = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        when(productService.deleteProduct(1L)).thenReturn(true);
        ResponseEntity<Void> actual = productController.deleteProduct(1L);

        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    @Test void deleteProductNotFound() {
        ResponseEntity<ProductEntity> expected = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        when(productService.deleteProduct(1L)).thenReturn(false);
        ResponseEntity<Void> actual = productController.deleteProduct(1L);

        assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }

    private ProductRequest getProductRequest() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("testName");
        productRequest.setPrice(12.34);
        return productRequest;
    }
}

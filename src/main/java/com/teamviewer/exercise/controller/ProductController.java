package com.teamviewer.exercise.controller;

import com.teamviewer.exercise.entity.ProductEntity;
import com.teamviewer.exercise.model.ProductRequest;
import com.teamviewer.exercise.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        List<ProductEntity> productEntities = productService.getAllProducts();
        return new ResponseEntity<>(productEntities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable Long id) {
        ProductEntity productEntity = productService.getProductById(id);
        if (productEntity != null) {
            return new ResponseEntity<>(productEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductRequest productRequest) {
        ProductEntity createdProductEntity = productService.createProduct(productRequest);
        if (createdProductEntity == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(createdProductEntity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest productRequest
    ) {
        ProductEntity updatedProductEntity = productService.updateProduct(id, productRequest);
        if (updatedProductEntity != null) {
            return new ResponseEntity<>(updatedProductEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

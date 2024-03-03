package com.teamviewer.exercise.service;

import com.teamviewer.exercise.entity.ProductEntity;
import com.teamviewer.exercise.model.ProductRequest;
import com.teamviewer.exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public ProductEntity createProduct(ProductRequest productRequest) {
        return productRepository.save(productRequest.toProductEntity());
    }

    public ProductEntity updateProduct(Long id, ProductRequest productRequest) {
        ProductEntity existingProduct = getProductById(id);
        if(existingProduct == null) return null;
        existingProduct.setName(productRequest.getName());
        existingProduct.setPrice(productRequest.getPrice());
        return productRepository.save(existingProduct);
    }

    public boolean deleteProduct(Long id) {
        ProductEntity existingProduct = getProductById(id);
        if(existingProduct == null) return false;
        productRepository.deleteById(id);
        return true;
    }
}

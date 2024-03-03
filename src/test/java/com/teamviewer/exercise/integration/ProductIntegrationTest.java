package com.teamviewer.exercise.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamviewer.exercise.controller.ProductController;
import com.teamviewer.exercise.entity.ProductEntity;
import com.teamviewer.exercise.model.ProductRequest;
import com.teamviewer.exercise.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(ProductController.class)
class ProductIntegrationTest {

    @Autowired private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test void getAllProducts() throws Exception {
        String expectedString = "[{\"id\":1,\"name\":\"name1\",\"price\":10.0}]";
        when(productService.getAllProducts()).thenReturn(getProductEntities());
        mockMvc.perform(MockMvcRequestBuilders.get("/products")).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(expectedString));
    }

    @Test void getProductById() throws Exception {
        String expectedString = "{\"id\":1,\"name\":\"name1\",\"price\":10.0}";
        when(productService.getProductById(1L)).thenReturn(getProductEntities().get(0));
        mockMvc.perform(MockMvcRequestBuilders.get("/products/1")).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(expectedString));
    }

    @Test void createProduct() throws Exception {
        String expectedString = "{\"id\":1,\"name\":\"name1\",\"price\":10.0}";
        when(productService.createProduct(any()))
            .thenReturn(getProductEntities().get(0));

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(getProductRequest()))
            )
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.content().string(expectedString));
    }

    @Test void updateProduct() throws Exception {
        String expectedString = "{\"id\":1,\"name\":\"name1\",\"price\":10.0}";
        when(productService.updateProduct(any(), any()))
            .thenReturn(getProductEntities().get(0));

        mockMvc.perform(
                MockMvcRequestBuilders
                    .put("/products/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(getProductRequest()))
            )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(expectedString));
    }

    @Test void deleteProduct() throws Exception {
        when(productService.deleteProduct(1L)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/1"))
            .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    private List<ProductEntity> getProductEntities() {
        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(new ProductEntity(1L, "name1", 10.0));
        return productEntities;
    }

    private ProductRequest getProductRequest() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("testName");
        productRequest.setPrice(12.34);
        return productRequest;
    }
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

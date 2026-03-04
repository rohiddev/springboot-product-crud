package com.demo.product;

import com.demo.product.exception.ResourceNotFoundException;
import com.demo.product.model.Product;
import com.demo.product.repository.ProductRepository;
import com.demo.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("Test Laptop", "A test laptop", 999.99, "Electronics", 10);
    }

    @Test
    void getAllProducts_returnsList() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        List<Product> result = productService.getAllProducts();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Test Laptop");
    }

    @Test
    void getProductById_found_returnsProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Product result = productService.getProductById(1L);
        assertThat(result.getName()).isEqualTo("Test Laptop");
    }

    @Test
    void getProductById_notFound_throwsException() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> productService.getProductById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void createProduct_savesAndReturnsProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);
        Product result = productService.createProduct(product);
        assertThat(result.getName()).isEqualTo("Test Laptop");
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void deleteProduct_notFound_throwsException() {
        when(productRepository.existsById(99L)).thenReturn(false);
        assertThatThrownBy(() -> productService.deleteProduct(99L))
                .isInstanceOf(ResourceNotFoundException.class);
        verify(productRepository, never()).deleteById(any());
    }

    @Test
    void deleteProduct_exists_callsDelete() {
        when(productRepository.existsById(1L)).thenReturn(true);
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
}

package com.example.productorderservice.product;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.productorderservice.product.application.port.ProductPort;
import com.example.productorderservice.product.application.service.GetProductResponse;
import com.example.productorderservice.product.application.service.ProductService;
import com.example.productorderservice.product.application.service.UpdateProductRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPort productPort;

    @Test
    void 상품수정() {

        productService.addProduct(ProductSteps.상품등록요청_생성());
        final Long productId = 1L;
        final UpdateProductRequest request = ProductSteps.상품수정_요청();

        productService.updateProduct(productId, request);

        final ResponseEntity<GetProductResponse> response = productService.getProduct(productId);
        final GetProductResponse productResponse = response.getBody();
        assertThat(productResponse.name()).isEqualTo("상품 수정");
        assertThat(productResponse.price()).isEqualTo(2000);
    }

}

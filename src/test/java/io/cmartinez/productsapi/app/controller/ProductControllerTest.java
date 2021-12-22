package io.cmartinez.productsapi.app.controller;

import io.cmartinez.productsapi.app.request.ProductRequest;
import io.cmartinez.productsapi.domain.data.ProductDto;
import io.cmartinez.productsapi.domain.port.service.ProductServicePort;
import io.cmartinez.productsapi.infra.exception.persistence.NotFoundException;
import io.cmartinez.productsapi.infra.mapper.Mapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductControllerTest {

    private ProductController productController;
    private ProductServicePort productServicePort;

    @BeforeAll
    void setUp() {
        productServicePort = Mockito.mock(ProductServicePort.class);
        productController = new ProductController(productServicePort);
    }

    @Test
    void getAll() {
        Mockito.when(productServicePort.getAll()).thenReturn(List.of(getProductDto()));
        ResponseEntity<List<ProductDto>> all = productController.getAll();

        assertEquals(HttpStatus.OK, all.getStatusCode());
        assertNotNull(all.getBody());
        assertEquals(1, all.getBody().size());
    }

    @Test
    void getBySku() {
        ProductDto productDto = getProductDto();
        String sku = productDto.getSku();
        Mockito.when(productServicePort.getBySku(sku)).thenReturn(productDto);
        ResponseEntity<ProductDto> bySku = productController.getBySku(sku);

        assertEquals(HttpStatus.OK, bySku.getStatusCode());
        assertNotNull(bySku.getBody());
        assertEquals(bySku.getBody().getSku(), sku);
    }

    @Test
    void add() {
        ProductRequest productRequest = getProductRequest();
        ProductDto productDto = Mapper.PRODUCT.toProductDto(productRequest);
        Mockito.when(productServicePort.add(Mockito.any(ProductDto.class))).thenReturn(productDto);
        ResponseEntity<ProductDto> add = productController.add(productRequest);

        assertEquals(HttpStatus.CREATED, add.getStatusCode());
        assertNotNull(add.getBody());
        assertEquals(add.getBody(), productDto);
    }

    @Test
    void update() {
        ProductRequest productRequest = getProductRequest();
        String sku = productRequest.getSku();
        ProductDto productDto = Mapper.PRODUCT.toProductDto(productRequest);
        ProductDto any = Mockito.any(ProductDto.class);
        Mockito.when(productServicePort.update(Mockito.eq(sku), any)).thenReturn(productDto);
        ResponseEntity<ProductDto> update = productController.update(sku, productRequest);

        assertEquals(HttpStatus.NO_CONTENT, update.getStatusCode());
        assertNull(update.getBody());
    }

    @Test
    void delete() {
        ProductRequest productRequest = getProductRequest();
        String sku = productRequest.getSku();
        ProductDto productDto = Mapper.PRODUCT.toProductDto(productRequest);
        Mockito.when(productServicePort.deleteBySku(sku)).thenReturn(productDto);
        ResponseEntity<ProductDto> delete = productController.delete(sku);

        assertEquals(HttpStatus.OK, delete.getStatusCode());
        assertNotNull(delete.getBody());
        assertEquals(delete.getBody(), productDto);
    }

    private ProductDto getProductDto() {
        ProductDto dto = new ProductDto();
        dto.setSize("XL");
        dto.setName("Polera");
        dto.setBrand("Maui and Sons");
        dto.setSku("FAL-10000000");
        dto.setPrice(10000.00);
        dto.setPrincipalImage("http://una-url.com/de-una-imagen.jpg");
        return dto;
    }

    private ProductRequest getProductRequest() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setSize("XL");
        productRequest.setName("Polera");
        productRequest.setBrand("Maui and Sons");
        productRequest.setSku("FAL-10000000");
        productRequest.setPrice(10000.00);
        productRequest.setPrincipalImage("http://una-url.com/de-una-imagen.jpg");
        return productRequest;
    }
}
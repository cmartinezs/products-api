package io.cmartinez.productsapi.domain.service;

import io.cmartinez.productsapi.domain.data.ProductDto;
import io.cmartinez.productsapi.domain.port.persistence.ProductPersistencePort;
import io.cmartinez.productsapi.domain.port.service.ProductServicePort;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductServiceImplTest {

    private ProductPersistencePort productPersistencePort;
    private ProductServicePort productService;

    @BeforeAll
    void setUp() {
        productPersistencePort = Mockito.mock(ProductPersistencePort.class);
        productService = new ProductServiceImpl(productPersistencePort);
    }

    @Test
    void getBySku() {
        ProductDto productDto = getProductDto();
        String sku = productDto.getSku();
        Mockito.when(productPersistencePort.getBySku(sku)).thenReturn(productDto);
        ProductDto bySku = productService.getBySku(sku);

        assertNotNull(bySku);
        assertEquals(productDto, bySku);
    }

    @Test
    void deleteBySku() {
        ProductDto productDto = getProductDto();
        String sku = productDto.getSku();
        Mockito.when(productPersistencePort.deleteBySku(sku)).thenReturn(productDto);
        ProductDto deletedProduct = productService.deleteBySku(sku);

        assertNotNull(deletedProduct);
        assertEquals(productDto, deletedProduct);
    }

    @Test
    void add() {
        ProductDto productDto = getProductDto();
        Mockito.when(productPersistencePort.add(productDto)).thenReturn(productDto);
        ProductDto add = productService.add(productDto);

        assertNotNull(add);
        assertEquals(add, productDto);
    }

    @Test
    void getAll() {
        Mockito.when(productPersistencePort.getAll()).thenReturn(List.of(getProductDto()));
        List<ProductDto> all = productService.getAll();

        assertNotNull(all);
        assertEquals(1, all.size());
    }

    @Test
    void update() {
        ProductDto productDto = getProductDto();
        String sku = productDto.getSku();
        ProductDto any = Mockito.any(ProductDto.class);
        Mockito.when(productPersistencePort.update(Mockito.eq(sku), any)).thenReturn(productDto);
        ProductDto update = productService.update(sku, productDto);

        assertNotNull(update);
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
}
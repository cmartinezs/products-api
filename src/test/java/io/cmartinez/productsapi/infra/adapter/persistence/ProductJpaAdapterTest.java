package io.cmartinez.productsapi.infra.adapter.persistence;

import io.cmartinez.productsapi.domain.data.ProductDto;
import io.cmartinez.productsapi.domain.port.persistence.ProductPersistencePort;
import io.cmartinez.productsapi.infra.entity.Product;
import io.cmartinez.productsapi.infra.exception.persistence.NotFoundException;
import io.cmartinez.productsapi.infra.mapper.Mapper;
import io.cmartinez.productsapi.infra.repository.ProductImageRepository;
import io.cmartinez.productsapi.infra.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductJpaAdapterTest {


    private ProductRepository productRepository;
    private ProductImageRepository productImageRepository;
    private ProductPersistencePort productJpaAdapter;

    @BeforeAll
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        productImageRepository = Mockito.mock(ProductImageRepository.class);
        productJpaAdapter = new ProductJpaAdapter(productRepository, productImageRepository);
    }

    @Test
    void getBySku() {
        ProductDto productDto = getProductDto();
        String sku = productDto.getSku();
        Product value = Mapper.PRODUCT.toProduct(productDto);
        value.setId(1L);
        Mockito.when(productRepository.getBySku(sku)).thenReturn(Optional.of(value));
        ProductDto bySku = productJpaAdapter.getBySku(sku);

        assertNotNull(bySku);
        assertEquals(sku, bySku.getSku());
    }

    @Test
    void getBySku_notFound() {
        ProductDto productDto = getProductDto();
        String sku = productDto.getSku();
        Mockito.when(productRepository.getBySku(sku)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productJpaAdapter.getBySku(sku));
    }

    @Test
    void deleteBySku() {
        ProductDto productDto = getProductDto();
        String sku = productDto.getSku();
        Product value = Mapper.PRODUCT.toProduct(productDto);
        value.setId(1L);
        Mockito.when(productRepository.getBySku(sku)).thenReturn(Optional.of(value));
        ProductDto deleted = productJpaAdapter.deleteBySku(sku);

        assertNotNull(deleted);
        assertEquals(sku, deleted.getSku());
    }

    @Test
    void deleteBySku_notFound() {
        ProductDto productDto = getProductDto();
        String sku = productDto.getSku();
        Mockito.when(productRepository.getBySku(sku)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productJpaAdapter.deleteBySku(sku));
    }

    @Test
    void add() {
        ProductDto productDto = getProductDto();
        Product value = Mapper.PRODUCT.toProduct(productDto);
        value.setId(1L);
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(value);
        ProductDto add = productJpaAdapter.add(productDto);

        assertNotNull(add);
    }

    @Test
    void getAll() {
        Product e1 = Mapper.PRODUCT.toProduct(getProductDto());
        e1.setId(1L);
        Mockito.when(productRepository.findAll()).thenReturn(List.of(e1));
        List<ProductDto> all = productJpaAdapter.getAll();

        assertNotNull(all);
        assertEquals(1, all.size());
    }

    @Test
    void update() {

        ProductDto productDto = getProductDto();
        String sku = productDto.getSku();
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(Mapper.PRODUCT.toProduct(productDto));
        Mockito.when(productRepository.getBySku(sku)).thenReturn(Optional.of(Mapper.PRODUCT.toProduct(productDto)));
        ProductDto update = productJpaAdapter.update(sku, productDto);

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
        dto.setOtherImages(List.of("http://otra-url.com/de-una-imagen.jpg", "http://otras-url.com/de-otra-imagen.jpg"));
        return dto;
    }
}
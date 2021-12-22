package io.cmartinez.productsapi.domain.port.persistence;

import io.cmartinez.productsapi.domain.data.ProductDto;

import java.util.List;

/**
 * @author Carlos
 * @version 1.0
 */
public interface ProductPersistencePort {

    ProductDto getBySku(String sku);

    ProductDto deleteBySku(String sku);

    ProductDto add(ProductDto productDto);

    List<ProductDto> getAll();

    ProductDto update(String sku, ProductDto productDto);
}

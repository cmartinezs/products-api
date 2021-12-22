package io.cmartinez.productsapi.domain.service;

import io.cmartinez.productsapi.domain.data.ProductDto;
import io.cmartinez.productsapi.domain.port.persistence.ProductPersistencePort;
import io.cmartinez.productsapi.domain.port.service.ProductServicePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author Carlos
 * @version 1.0
 */

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductServicePort {

    private final ProductPersistencePort productPersistencePort;

    @Override
    public ProductDto getBySku(String sku) {
        return productPersistencePort.getBySku(sku);
    }

    @Override
    public ProductDto deleteBySku(String sku) {
        return productPersistencePort.deleteBySku(sku);
    }

    @Override
    public ProductDto add(ProductDto productDto) {
        return productPersistencePort.add(productDto);
    }

    @Override
    public List<ProductDto> getAll() {
        return productPersistencePort.getAll();
    }

    @Override
    public ProductDto update(String sku, ProductDto productDto) {
        return productPersistencePort.update(sku, productDto);
    }
}

package io.cmartinez.productsapi.infra.adapter.persistence;

import io.cmartinez.productsapi.domain.data.ProductDto;
import io.cmartinez.productsapi.domain.port.persistence.ProductPersistencePort;
import io.cmartinez.productsapi.infra.entity.Product;
import io.cmartinez.productsapi.infra.entity.ProductImage;
import io.cmartinez.productsapi.infra.exception.persistence.NotFoundException;
import io.cmartinez.productsapi.infra.mapper.Mapper;
import io.cmartinez.productsapi.infra.repository.ProductImageRepository;
import io.cmartinez.productsapi.infra.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Carlos
 * @version 1.0
 */

@RequiredArgsConstructor
public class ProductJpaAdapter implements ProductPersistencePort {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    @Override
    public ProductDto getBySku(String sku) {
        return Mapper.PRODUCT.toProductDto(getProduct(sku));
    }

    @Override
    public ProductDto deleteBySku(String sku) {
        Product product = getProduct(sku);
        productImageRepository.deleteAll(product.getImages());
        productRepository.delete(product);
        return Mapper.PRODUCT.toProductDto(product);
    }

    private Product getProduct(String sku) {
        return productRepository.getBySku(sku).orElseThrow(() -> new NotFoundException(String.format("There is no product with sku %s", sku)));
    }

    @Override
    public ProductDto add(ProductDto productDto) {
        Product entity = Mapper.PRODUCT.toProduct(productDto);
        Product savedProduct = productRepository.save(entity);
        entity.getImages().forEach(productImage -> productImage.setProduct(entity));
        productImageRepository.saveAll(entity.getImages());
        return Mapper.PRODUCT.toProductDto(savedProduct);
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return Mapper.PRODUCT.toProductListDto(products);
    }

    @Override
    public ProductDto update(String sku, ProductDto productDto) {
        Product product = getProduct(sku);
        product.setBrand(productDto.getBrand());
        product.setImage(productDto.getPrincipalImage());
        product.setImages(productDto.getOtherImages().stream().map(productImageToString()).collect(Collectors.toList()));
        product.setName(productDto.getName());
        product.setSize(productDto.getSize());
        productRepository.save(product);
        product.getImages().forEach(productImage -> productImage.setProduct(product));
        return Mapper.PRODUCT.toProductDto(product);
    }

    private Function<String, ProductImage> productImageToString() {
        return image -> ProductImage.builder().url(image).build();
    }
}

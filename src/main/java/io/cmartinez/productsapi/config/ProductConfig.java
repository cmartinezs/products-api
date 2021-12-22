package io.cmartinez.productsapi.config;

import io.cmartinez.productsapi.domain.port.persistence.ProductPersistencePort;
import io.cmartinez.productsapi.domain.port.service.ProductServicePort;
import io.cmartinez.productsapi.domain.service.ProductServiceImpl;
import io.cmartinez.productsapi.infra.adapter.persistence.ProductJpaAdapter;
import io.cmartinez.productsapi.infra.repository.ProductImageRepository;
import io.cmartinez.productsapi.infra.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Carlos
 * @version 1.0
 */

@Configuration
@RequiredArgsConstructor
public class ProductConfig {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    @Bean
    public ProductPersistencePort productJpaAdapter() {
        return new ProductJpaAdapter(productRepository, productImageRepository);
    }

    @Bean
    public ProductServicePort productServicePort() {
        return new ProductServiceImpl(productJpaAdapter());
    }
}

package io.cmartinez.productsapi.infra.repository;

import io.cmartinez.productsapi.infra.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Carlos
 * @version 1.0
 */

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> getBySku(String sku);
}

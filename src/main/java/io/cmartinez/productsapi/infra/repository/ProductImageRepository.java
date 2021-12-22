package io.cmartinez.productsapi.infra.repository;

import io.cmartinez.productsapi.infra.entity.ProductImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Carlos
 * @version 1.0
 */

@Repository
public interface ProductImageRepository extends CrudRepository<ProductImage, Long> {

}

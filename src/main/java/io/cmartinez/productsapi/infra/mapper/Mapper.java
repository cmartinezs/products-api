package io.cmartinez.productsapi.infra.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;

/**
 * @author Carlos
 * @version 1.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mapper {

    public static final ProductMapper PRODUCT = Mappers.getMapper(ProductMapper.class);
}

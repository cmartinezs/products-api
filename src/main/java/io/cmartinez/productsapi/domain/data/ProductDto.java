package io.cmartinez.productsapi.domain.data;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

/**
 * @author Carlos
 * @version 1.0
 */

@Getter
@Setter
public class ProductDto {
    private String sku;
    private String name;
    private String brand;
    private String size;
    private double price;
    private String principalImage;
    private List<String> otherImages = Collections.emptyList();
}

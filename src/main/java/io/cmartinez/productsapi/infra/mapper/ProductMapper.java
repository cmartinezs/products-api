package io.cmartinez.productsapi.infra.mapper;

import io.cmartinez.productsapi.app.request.ProductRequest;
import io.cmartinez.productsapi.domain.data.ProductDto;
import io.cmartinez.productsapi.infra.entity.Product;
import io.cmartinez.productsapi.infra.entity.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Carlos
 * @version 1.0
 */

@Mapper
public interface ProductMapper {

    @Mapping(target = "principalImage", source = "image")
    @Mapping(target = "otherImages", source = "images")
    ProductDto toProductDto(Product product);

    ProductDto toProductDto(ProductRequest product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", source = "principalImage")
    @Mapping(target = "images", source = "otherImages")
    Product toProduct(ProductDto productDto);

    List<ProductDto> toProductListDto(List<Product> product);

    List<Product> toProductList(List<ProductDto> productDto);

    default String fromProductImage(ProductImage productImage) {
        return productImage != null ? productImage.getUrl() : null;
    }

    default ProductImage fromString(String image) {
        return ProductImage.builder().url(image).build();
    }

    default List<String> mapImages(Product product) {
        return product.getImages().stream().map(ProductImage::getUrl).collect(Collectors.toList());
    }

    default List<ProductImage> mapImages(ProductDto product) {
        return product.getOtherImages()
                .stream()
                .map(s -> ProductImage.builder().url(s).build())
                .collect(Collectors.toList());
    }
}

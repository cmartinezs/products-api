package io.cmartinez.productsapi.app.controller;

import io.cmartinez.productsapi.app.request.ProductRequest;
import io.cmartinez.productsapi.domain.data.ProductDto;
import io.cmartinez.productsapi.domain.port.service.ProductServicePort;
import io.cmartinez.productsapi.infra.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Carlos
 * @version 1.0
 */

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServicePort productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProductDto> getBySku(@PathVariable("sku") String sku){
        return ResponseEntity.ok(productService.getBySku(sku));
    }

    @PostMapping
    public ResponseEntity<ProductDto> add(@Valid @RequestBody ProductRequest productRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.add(Mapper.PRODUCT.toProductDto(productRequest)));
    }

    @PutMapping("/{sku}")
    public ResponseEntity<ProductDto> update(@PathVariable("sku") String sku, @Valid @RequestBody ProductRequest productRequest){
        productService.update(sku, Mapper.PRODUCT.toProductDto(productRequest));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<ProductDto> delete(@PathVariable("sku") String sku) {
        return ResponseEntity.ok(productService.deleteBySku(sku));
    }
}

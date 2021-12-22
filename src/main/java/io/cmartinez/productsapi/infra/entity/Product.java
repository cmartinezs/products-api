package io.cmartinez.productsapi.infra.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

/**
 * @author Carlos
 * @version 1.0
 */

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "sku", unique = true, nullable = false, length = 13)
    private String sku;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "brand", nullable = false, length = 50)
    private String brand;

    @Column(name = "size")
    private String size;

    @Column(name = "price")
    private double price;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> images = Collections.emptyList();
}

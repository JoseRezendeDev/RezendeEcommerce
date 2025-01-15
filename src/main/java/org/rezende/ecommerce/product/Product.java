package org.rezende.ecommerce.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private String id;
    private String name;
    private int quantity;
    private BigDecimal price;
}

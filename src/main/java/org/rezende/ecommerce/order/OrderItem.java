package org.rezende.ecommerce.order;

import lombok.Data;

@Data
public class OrderItem {
    private String productId;
    private int quantity;
}

package org.rezende.ecommerce.product;

import jakarta.persistence.*;
import lombok.Data;
import org.rezende.ecommerce.order.orderitem.OrderItem;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private int quantity;
    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;
}

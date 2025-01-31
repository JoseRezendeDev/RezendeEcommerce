package org.rezende.ecommerce.order.orderitem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.rezende.ecommerce.order.Order;
import org.rezende.ecommerce.product.Product;

import java.io.Serializable;

@Data
@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    // Used to avoid circular reference
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
}

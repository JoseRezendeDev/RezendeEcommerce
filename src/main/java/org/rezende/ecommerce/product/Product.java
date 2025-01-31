package org.rezende.ecommerce.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.rezende.ecommerce.order.orderitem.OrderItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private int quantity;
    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    // Used to avoid circular reference
    @JsonIgnore
    private List<OrderItem> orderItems;
}

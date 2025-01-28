package org.rezende.ecommerce.customer;

import jakarta.persistence.*;
import lombok.Data;
import org.rezende.ecommerce.order.Order;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Order> orders;
}

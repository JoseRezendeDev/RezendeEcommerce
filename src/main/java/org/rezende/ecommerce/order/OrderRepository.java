package org.rezende.ecommerce.order;

import org.rezende.ecommerce.utils.LoadOrders;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class OrderRepository {

    private final List<Order> orders;

    public OrderRepository() throws IOException {
        orders = LoadOrders.loadOrdersFromJson();
    }

    public List<Order> getOrders() {
        return orders;
    }
}

package org.rezende.ecommerce.order;

import org.apache.coyote.BadRequestException;

import java.util.List;

public class OrderRepositoryPostgresImpl implements OrderRepository {
    @Override
    public List<Order> getOrders() {
        return List.of();
    }

    @Override
    public Order createOrder(Order order) throws BadRequestException {
        return null;
    }
}

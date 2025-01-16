package org.rezende.ecommerce.order;

import org.apache.coyote.BadRequestException;

import java.util.List;

public interface OrderRepository {
    List<Order> getOrders();

    Order createOrder(Order order) throws BadRequestException;
}

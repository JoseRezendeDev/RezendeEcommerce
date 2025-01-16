package org.rezende.ecommerce.order;

import org.apache.coyote.BadRequestException;
import org.rezende.ecommerce.utils.InitialLoad;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryInternalImpl implements OrderRepository {

    private List<Order> orders;

    public OrderRepositoryInternalImpl() throws IOException {
        orders = InitialLoad.loadFromJson("src/main/resources/orders.json", Order.class);
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public Order createOrder(Order order) throws BadRequestException {
        if (order.getId() == 0L) {
            Optional<Order> maxIdOrder = orders.stream().max(Comparator.comparingLong(Order::getId));

            if (maxIdOrder.isPresent()) {
                order.setId(maxIdOrder.get().getId() + 1);
            } else {
                order.setId(1L);
            }
        }

        orders.add(order);

        return orders.stream().filter(o -> o.getId() == order.getId()).findFirst()
                .orElseThrow(() -> new BadRequestException("Failed to create order"));
    }
}

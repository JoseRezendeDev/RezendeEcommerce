package org.rezende.ecommerce.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.rezende.ecommerce.order.Order;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LoadOrders {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<Order> loadOrdersFromJson() throws IOException {
        Order[] orders = objectMapper.readValue(new File("src/main/resources/orders.json"), Order[].class);

        return List.of(orders);
    }
}

package org.rezende.ecommerce.order;

import org.apache.coyote.BadRequestException;
import org.rezende.ecommerce.order.dto.CreateOrderRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequestDTO createOrderRequestDTO) throws BadRequestException {
        Order createdOrder = orderService.createOrder(createOrderRequestDTO);
        return ResponseEntity.ok(createdOrder);
    }
}

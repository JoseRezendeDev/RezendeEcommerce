package org.rezende.ecommerce.order;

import org.apache.coyote.BadRequestException;
import org.rezende.ecommerce.customer.CustomerService;
import org.rezende.ecommerce.order.dto.CreateOrderRequestDTO;
import org.rezende.ecommerce.product.Product;
import org.rezende.ecommerce.product.ProductService;
import org.rezende.ecommerce.product.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, CustomerService customerService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    public List<Order> getOrders() {
        return orderRepository.getOrders();
    }

    public Order createOrder(CreateOrderRequestDTO createOrderRequestDTO) throws BadRequestException {
        validateRequest(createOrderRequestDTO);

        checkAvailabilityInStock(createOrderRequestDTO.getProducts());

        Order order = createOrderFromRequest(createOrderRequestDTO);

        return orderRepository.createOrder(order);
    }

    private Order createOrderFromRequest(CreateOrderRequestDTO createOrderRequestDTO) throws BadRequestException {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);
        order.setCustomer(customerService.getCustomerById(createOrderRequestDTO.getCustomerId()));
        order.setItems(createOrderRequestDTO.getProducts().stream().map(this::productDTOToOrderItem).toList());
        return orderRepository.createOrder(order);
    }

    private void checkAvailabilityInStock(List<ProductDTO> productDTOS) {
        productDTOS.forEach(productDTO -> {
            Product product = productService.getProducts().stream().filter(p -> p.getId().equals(productDTO.getId()))
                    .findFirst().orElse(null);

            if (product == null || product.getQuantity() < productDTO.getQuantity()) {
                throw new RuntimeException("There is not enough quantity of the product in stock!");
            }
        });
    }

    private OrderItem productDTOToOrderItem(ProductDTO productDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(productDTO.getId());
        orderItem.setQuantity(productDTO.getQuantity());
        return orderItem;
    }

    private void validateRequest(CreateOrderRequestDTO createOrderRequestDTO) throws BadRequestException {
        if (createOrderRequestDTO.getCustomerId() == null
                || createOrderRequestDTO.getProducts() == null
                || createOrderRequestDTO.getProducts().isEmpty()) {
            throw new BadRequestException("The fields customerId, productId and quantity should be filled!");
        }
    }
}

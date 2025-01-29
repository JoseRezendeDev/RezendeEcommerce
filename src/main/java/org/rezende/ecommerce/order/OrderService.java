package org.rezende.ecommerce.order;

import org.apache.coyote.BadRequestException;
import org.rezende.ecommerce.customer.Customer;
import org.rezende.ecommerce.customer.CustomerService;
import org.rezende.ecommerce.order.dto.CreateOrderRequestDTO;
import org.rezende.ecommerce.order.orderitem.OrderItem;
import org.rezende.ecommerce.order.orderitem.OrderItemRepository;
import org.rezende.ecommerce.product.Product;
import org.rezende.ecommerce.product.ProductService;
import org.rezende.ecommerce.product.dto.ProductDTO;
import org.rezende.ecommerce.utils.InitialLoad;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    @Value("${config.jose.my-test}")
    private String joseConfig;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, CustomerService customerService, ProductService productService) throws IOException {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerService = customerService;
        this.productService = productService;

        loadOrders();
    }

    private void loadOrders() throws IOException {
        List<Order> orders = InitialLoad.loadFromJson("src/main/resources/orders.json", Order.class);
        setCustomers(orders);
        setOrderItems(orders);
        orderRepository.saveAll(orders);
        orderItemRepository.saveAll(orders.stream().flatMap(order -> order.getItems().stream()).toList());
    }

    private void setOrderItems(List<Order> orders) {
        orders.forEach(order -> order.getItems().forEach(orderItem -> {
            Product product = productService.getProductByName(orderItem.getProduct().getName());
            orderItem.setProduct(product);
            orderItem.setOrder(order);
        }));
    }

    private void setCustomers(List<Order> orders) {
        orders.forEach(order -> {
            Customer customer = customerService.getCustomerByName(order.getCustomer().getName());
            order.setCustomer(customer);
        });
    }

    // TODO Call 3rd party API

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(CreateOrderRequestDTO createOrderRequestDTO) throws BadRequestException {
        validateRequest(createOrderRequestDTO);

        checkAvailabilityInStock(createOrderRequestDTO.getProducts());

        Order order = createOrderFromRequest(createOrderRequestDTO);

        return orderRepository.save(order);
    }

    private Order createOrderFromRequest(CreateOrderRequestDTO createOrderRequestDTO) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);
        order.setCustomer(customerService.getCustomerById(createOrderRequestDTO.getCustomerId()));
        order.setItems(createOrderRequestDTO.getProducts().stream().map(this::productDTOToOrderItem).toList());
        return orderRepository.save(order);
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
        orderItem.setProduct(productService.getProductById(productDTO.getId()));
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

package org.rezende.ecommerce.order.orderitem;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends ListCrudRepository<OrderItem, Long> {

}

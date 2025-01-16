package org.rezende.ecommerce.customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> getCustomers();

    Customer getCustomerById(String id);
}

package org.rezende.ecommerce.customer;

import org.rezende.ecommerce.utils.InitialLoad;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class CustomerRepositoryInternalImpl implements CustomerRepository {

    private List<Customer> customers;

    public CustomerRepositoryInternalImpl() throws IOException {
        customers = InitialLoad.loadFromJson("src/main/resources/customers.json", Customer.class);
    }

    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public Customer getCustomerById(String id) {
        return customers.stream().filter(customer -> customer.getId().equals(id)).findFirst()
                .orElse(null);
    }
}

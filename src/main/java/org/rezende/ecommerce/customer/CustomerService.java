package org.rezende.ecommerce.customer;

import jakarta.persistence.EntityNotFoundException;
import org.rezende.ecommerce.utils.InitialLoad;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) throws IOException {
        this.customerRepository = customerRepository;
        List<Customer> customers = InitialLoad.loadFromJson("src/main/resources/customers.json", Customer.class);
        customerRepository.saveAll(customers);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found!"));
    }

    public Customer getCustomerByName(String name) {
        return customerRepository.findByName(name);
    }
}

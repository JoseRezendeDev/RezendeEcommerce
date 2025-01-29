package org.rezende.ecommerce.customer;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ListCrudRepository<Customer, String> {
    Customer findByName(String name);
}

package org.rezende.ecommerce.product;

import java.util.List;

public interface ProductRepository {
    List<Product> getProducts();

    Product getProductById(String id);
}

package org.rezende.ecommerce.product;

import org.rezende.ecommerce.utils.InitialLoad;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class ProductRepositoryInternalImpl implements ProductRepository {

    private List<Product> stockProducts;

    public ProductRepositoryInternalImpl() throws IOException {
        stockProducts = InitialLoad.loadFromJson("src/main/resources/products.json", Product.class);
    }

    @Override
    public List<Product> getProducts() {
        return stockProducts;
    }

    @Override
    public Product getProductById(String id) {
        return stockProducts.stream().filter(product -> product.getId().equals(id)).findFirst()
                .orElse(null);
    }
}

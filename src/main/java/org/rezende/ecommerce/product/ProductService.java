package org.rezende.ecommerce.product;

import jakarta.persistence.EntityNotFoundException;
import org.rezende.ecommerce.utils.InitialLoad;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) throws IOException {
        this.productRepository = productRepository;
        List<Product> products = InitialLoad.loadFromJson("src/main/resources/products.json", Product.class);
        productRepository.saveAll(products);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found!"));
    }

    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }
}

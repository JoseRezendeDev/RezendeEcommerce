package org.rezende.ecommerce.order.dto;

import lombok.Data;
import org.rezende.ecommerce.product.dto.ProductDTO;

import java.util.List;

@Data
public class CreateOrderRequestDTO {
    private String customerId;
    private List<ProductDTO> products;
}

package org.rezende.ecommerce.externalapi;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ExternalProduct implements Serializable {
    private int id;
    private String title;
    private BigDecimal price;
    private String description;
}

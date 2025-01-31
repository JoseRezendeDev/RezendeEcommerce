package org.rezende.ecommerce.externalapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("external-products")
public class ExternalProductController {

    private final ExternalProductService externalProductService;

    public ExternalProductController(ExternalProductService externalProductService) {
        this.externalProductService = externalProductService;
    }

    @GetMapping
    public ResponseEntity<List<ExternalProduct>> getExternalProducts() throws JsonProcessingException {
        List<ExternalProduct> externalProducts = externalProductService.getExternalProducts();
        return ResponseEntity.ok(externalProducts);
    }

    @PostMapping
    public ResponseEntity<ExternalProduct> createExternalProduct(@RequestBody ExternalProduct externalProduct) {
        ExternalProduct createdExternalProduct = externalProductService.createExternalProduct(externalProduct);
        return ResponseEntity.ok(createdExternalProduct);
    }
}

package org.rezende.ecommerce.externalapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service created to practice integrating with a 3rd party API
 *
 * @author Jose Rezende
 */
@Service
public class ExternalProductService {

    private final ExternalProductRestClient restClient;

    public ExternalProductService(ExternalProductRestClient restClient) {
        this.restClient = restClient;
    }

    public List<ExternalProduct> getExternalProducts() throws JsonProcessingException {
        return restClient.getExternalProducts();
    }

    public ExternalProduct createExternalProduct(ExternalProduct externalProduct) {
        return restClient.createExternalProduct(externalProduct);
    }
}

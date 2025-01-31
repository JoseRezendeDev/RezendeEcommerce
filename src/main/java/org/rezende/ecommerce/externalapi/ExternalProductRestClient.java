package org.rezende.ecommerce.externalapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ExternalProductRestClient {

    private final String baseUrl = "https://fakestoreapi.com";

    private final ObjectMapper objectMapper;
    private final RestClient restClient;

    public ExternalProductRestClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.restClient = RestClient.create();
    }

    public List<ExternalProduct> getExternalProducts() throws JsonProcessingException {
        String jsonResponse = restClient
                .get()
                .uri(baseUrl + "/products")
                .retrieve()
                .body(String.class);

        return objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });
    }

    public ExternalProduct createExternalProduct(ExternalProduct externalProduct) {
        return restClient
                .post()
                .uri(baseUrl + "/products")
                .contentType(MediaType.APPLICATION_JSON)
                .body(externalProduct)
                .retrieve()
                .body(ExternalProduct.class);
    }
}

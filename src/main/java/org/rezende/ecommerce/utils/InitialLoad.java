package org.rezende.ecommerce.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InitialLoad {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> List<T> loadFromJson(String path, Class<T> tClass) throws IOException {
        // This is needed because readValue cannot inference
        // object type of a list of T since it is generic
        CollectionType listType = objectMapper.getTypeFactory()
                .constructCollectionType(ArrayList.class, tClass);

        return objectMapper.readValue(new File(path), listType);
    }
}

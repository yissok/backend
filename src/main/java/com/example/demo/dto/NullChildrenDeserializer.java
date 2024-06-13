/* (C)2024 */
package com.example.demo.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.List;

public class NullChildrenDeserializer extends JsonDeserializer<List<String>> {
    @Override
    public List<String> deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        String value = p.getText();
        if ("nil".equals(value) || "null".equals(value)) {
            return null; // Return an empty list for 'nil' or 'null'
        }
        // Use the default deserializer for other cases
        return ctxt.readValue(
                p, ctxt.getTypeFactory().constructCollectionType(List.class, String.class));
    }
}

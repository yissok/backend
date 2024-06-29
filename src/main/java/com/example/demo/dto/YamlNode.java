package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class YamlNode {
    @JsonProperty("name")
    private String name;

    @JsonProperty("parent")
    private String parent;

    @JsonProperty("content")
    @JsonDeserialize(using = NullContentDeserializer.class)
    private String content;

    @JsonProperty("children")
    @JsonDeserialize(using = NullChildrenDeserializer.class)
    private List<String> children;

    public class NullChildrenDeserializer extends JsonDeserializer<List<String>> {
        @Override
        public List<String> deserialize(JsonParser p, DeserializationContext ctxt)
                throws IOException {
            String value = p.getText();
            if ("nil".equals(value) || "null".equals(value)) {
                return null;
            }
            return ctxt.readValue(
                    p, ctxt.getTypeFactory().constructCollectionType(List.class, String.class));
        }
    }

    public class NullContentDeserializer extends JsonDeserializer<String> {
        @Override
        public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            String value = p.getText();
            if ("nil".equals(value) || "null".equals(value)) {
                return null;
            }
            return value;
        }
    }
}

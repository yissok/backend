/* (C)2024 */
package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}

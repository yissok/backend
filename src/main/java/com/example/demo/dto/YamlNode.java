/* (C)2024 */
package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.List;

@Data
public class YamlNode {
    @JsonProperty("name")
    private String name;

    @JsonProperty("parent")
    private String parent;

    @JsonProperty("content")
    private String content;

    @JsonProperty("children")
    private List<String> children;
}

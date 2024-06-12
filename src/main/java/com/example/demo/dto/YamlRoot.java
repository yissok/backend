/* (C)2024 */
package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.List;

@Data
public class YamlRoot {
    @JsonProperty("nodes")
    private List<YamlNode> nodes;
}

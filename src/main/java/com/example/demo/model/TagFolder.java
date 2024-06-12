/* (C)2024 */
package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TagFolder extends Node {
    List<TagFolder> children;
}

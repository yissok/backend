/* (C)2024 */
package com.example.demo.model;

import lombok.Data;

@Data
public abstract class Node {
    String name;

    TagFolder parent;
}

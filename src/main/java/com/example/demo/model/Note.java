/* (C)2024 */
package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Note extends Node {
    String content;
}

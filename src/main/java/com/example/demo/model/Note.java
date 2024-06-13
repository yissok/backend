/* (C)2024 */
package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
public class Note extends Node {
    String content;
    public Note(String name, String content){
        this.name=name;
        this.content=content;
    }
}

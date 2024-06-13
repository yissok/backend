/* (C)2024 */
package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TagFolder extends Node {
    List<TagFolder> children=new ArrayList<>();
    public TagFolder(String name){
        this.name=name;
        children=new ArrayList<>();
    }
}

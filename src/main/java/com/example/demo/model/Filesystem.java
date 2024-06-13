/* (C)2024 */
package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class Filesystem {
    List<Note> notes=new ArrayList<>();
    List<TagFolder> tagFolders=new ArrayList<>();

}

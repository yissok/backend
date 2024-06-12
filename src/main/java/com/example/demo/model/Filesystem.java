/* (C)2024 */
package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Filesystem {
    List<Note> notes;
    List<TagFolder> tagFolders;

    public Filesystem(String dbFile) {}
}

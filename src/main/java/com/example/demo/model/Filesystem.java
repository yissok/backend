/* (C)2024 */
package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Data
public class Filesystem {
    Map<String, Note> notes = new HashMap<>();
    Map<String, TagFolder> tagFolders = new HashMap<>();

    //support
    Map<String, List<Note>> folderToNotes = new HashMap<>();
}

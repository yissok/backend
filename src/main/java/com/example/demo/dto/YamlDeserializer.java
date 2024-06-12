/* (C)2024 */
package com.example.demo.dto;

import com.example.demo.model.Filesystem;
import com.example.demo.model.Note;
import com.example.demo.model.TagFolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.*;

public class YamlDeserializer {

    public static Filesystem deserializeYaml(String yamlRootStr) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        YamlRoot yamlRoot = mapper.readValue(yamlRootStr, YamlRoot.class);

        Map<String, TagFolder> tagFolderMap = new HashMap<>();
        Map<String, Note> noteMap = new HashMap<>();

        // First pass to create all TagFolder and Note objects
        for (YamlNode yamlNode : yamlRoot.getNodes()) {
            if (yamlNode.getContent() == null) {
                TagFolder tagFolder = new TagFolder();
                tagFolder.setName(yamlNode.getName());
                tagFolderMap.put(yamlNode.getName(), tagFolder);
            } else {
                Note note = new Note();
                note.setName(yamlNode.getName());
                note.setContent(yamlNode.getContent());
                noteMap.put(yamlNode.getName(), note);
            }
        }

        // Second pass to set parent and children
        for (YamlNode yamlNode : yamlRoot.getNodes()) {
            if (yamlNode.getContent() == null) {
                TagFolder tagFolder = tagFolderMap.get(yamlNode.getName());
                if (yamlNode.getParent() != null) {
                    tagFolder.setParent(tagFolderMap.get(yamlNode.getParent()));
                }
                if (yamlNode.getChildren() != null) {
                    for (String childName : yamlNode.getChildren()) {
                        tagFolder.getChildren().add(tagFolderMap.get(childName));
                    }
                }
            } else {
                Note note = noteMap.get(yamlNode.getName());
                if (yamlNode.getParent() != null) {
                    note.setParent(tagFolderMap.get(yamlNode.getParent()));
                }
            }
        }

        Filesystem filesystem = new Filesystem();
        filesystem.setNotes(new ArrayList<>(noteMap.values()));
        filesystem.setTagFolders(new ArrayList<>(tagFolderMap.values()));

        return filesystem;
    }
}

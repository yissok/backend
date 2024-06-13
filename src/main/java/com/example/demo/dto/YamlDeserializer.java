/* (C)2024 */
package com.example.demo.dto;

import com.example.demo.model.Filesystem;
import com.example.demo.model.Note;
import com.example.demo.model.TagFolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.*;
import java.util.Stack;

public class YamlDeserializer {

    enum TreePart{
        TAG,NOTE,END
    }
    public static Filesystem deserializeYaml(String serializedTree) {
        Filesystem filesystem = new Filesystem();
        Stack<TagFolder> folderStack = new Stack<>();

        String[] items = serializedTree.trim().split("-");

        for (String el:items) {
            TreePart part = getTreePart(el);
            if (part==TreePart.TAG){
                TagFolder t = new TagFolder(el);
                if (!folderStack.isEmpty()){
                    TagFolder parent = folderStack.peek();
                    t.setParent(parent);
                    parent.getChildren().add(t);
                }
                folderStack.add(t);
                filesystem.getTagFolders().add(t);
            }
            if (part==TreePart.END){
                folderStack.pop();
            }
            if (part==TreePart.NOTE){
                Note n = new Note(getNoteName(el),getNoteContent(el));
                n.setParent(folderStack.peek());
                filesystem.getNotes().add(n);
            }
        }

        return filesystem;
    }

    private static String getNoteContent(String el) {
        return el.split(":")[1];
    }

    private static String getNoteName(String el) {
        return el.split(":")[0];
    }

    public static TreePart getTreePart(String str) {
        if (str.contains(":"))
            return TreePart.NOTE;
        else
            if(str.equals("_"))
                return TreePart.END;
            else
                return TreePart.TAG;
    }
}
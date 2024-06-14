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
import java.util.stream.Collectors;

public class YamlDeserializer {

    enum TreePart{
        TAG,NOTE, REMOVE, END
    }
    public static Filesystem deserializeYaml(String serializedTree, Filesystem existingFilesystem) {
        Filesystem filesystem = existingFilesystem==null?new Filesystem():existingFilesystem;
        Stack<TagFolder> folderStack = new Stack<>();

        String[] items = serializedTree.trim().split("-");

        for (String el:items) {
            TreePart part = getTreePart(el);
            if (part==TreePart.TAG){
                TagFolder t = new TagFolder(el);
                if (!folderStack.isEmpty()){
                    TagFolder parent = folderStack.peek();//todo instead, get parent name and fetch parent obj from tagfolder map
                    t.setParent(parent);
                    parent.getChildren().add(t);
                }
                folderStack.add(t);
                if (filesystem.getTagFolders().stream().filter(tagFolder->tagFolder.getName().equals(el)).toList().isEmpty())
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
            if (part==TreePart.REMOVE){
                final String elRm=el.substring(1);
                List<Note> noteList=filesystem.getNotes().stream().filter(note->note.getName().equals(elRm)).toList();
                if (!noteList.isEmpty()){
                    Note n = noteList.getFirst();
                    filesystem.getNotes().remove(n);
                } else {
                    List<TagFolder> tagFolderList=filesystem.getTagFolders().stream().filter(tagFolder->tagFolder.getName().equals(elRm)).toList();
                    if (!tagFolderList.isEmpty()){
                        TagFolder tf = tagFolderList.getFirst();
                        filesystem.getTagFolders().remove(tf);
                    } else {
                        System.out.println("I am not removing this -> "+el);
                    }
                }
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
        if (str.contains("!")) {

            return TreePart.REMOVE;
        } else {

            if (str.contains(":"))
                return TreePart.NOTE;
            else
            if(str.equals("_"))
                return TreePart.END;
            else
                return TreePart.TAG;
        }
    }
}
package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
@Data
public class Filesystem {

    public static String LB_ROOT = "ROOT";
    public static String SERIAL_SEPARATOR = "-";
    public static String SERIAL_BACKWARDS = "_";
    public static String SERIAL_CONTENT_SEPARATOR = ":";

    Map<String, Note> notes = new HashMap<>();
    Map<String, TagFolder> tagFolders = new HashMap<>();

    // support
    Map<String, List<Note>> folderToNotes = new HashMap<>();

    public static String serialiseTree(Filesystem filesystem) {
        return serialiseTreeRec(
                        filesystem,
                        filesystem.getTagFolders().get(LB_ROOT),
                        new StringBuilder(),
                        LB_ROOT)
                .toString();
    }

    public static StringBuilder serialiseTreeRec(
            Filesystem filesystem, TagFolder tf, StringBuilder serialised, String folderLevel) {
        serialised.append(folderLevel).append(SERIAL_SEPARATOR);
        // add notes
        List<Note> notes = filesystem.getFolderToNotes().get(folderLevel);
        if (notes != null)
            notes.forEach(
                    note ->
                            serialised
                                    .append(note.getName())
                                    .append(SERIAL_CONTENT_SEPARATOR)
                                    .append(note.getContent())
                                    .append(SERIAL_SEPARATOR));
        // add folders
        HashMap<String, TagFolder> folders = tf.getChildren();
        if (folders != null)
            folders.forEach(
                    (name, folder) -> {
                        serialiseTreeRec(filesystem, folder, serialised, name);
                    });
        serialised.append(SERIAL_BACKWARDS).append(SERIAL_SEPARATOR);
        return serialised;
    }

    enum TreePart {
        TAG,
        NOTE,
        REMOVE,
        END
    }

    public static Filesystem createTree(String serializedTree, Filesystem existingFilesystem) {
        Filesystem filesystem = existingFilesystem == null ? new Filesystem() : existingFilesystem;
        Stack<TagFolder> folderStack = new Stack<>();

        String[] items = serializedTree.trim().split("-");

        for (String el : items) {
            TreePart part = getTreePart(el);
            if (part == TreePart.TAG) {
                TagFolder t =
                        filesystem.getTagFolders().get(el) == null
                                ? new TagFolder(el)
                                : filesystem.getTagFolders().get(el);
                TagFolder parent =
                        folderStack.isEmpty()
                                ? null
                                : filesystem.getTagFolders().get(folderStack.peek().getName());
                t.setParent(parent);
                if (parent != null) parent.getChildren().put(el, t);
                folderStack.add(t);
                filesystem.getTagFolders().put(el, t);
            }
            if (part == TreePart.END) {
                folderStack.pop();
            }
            if (part == TreePart.NOTE) {
                String parentName = folderStack.peek().getName();
                Note n = new Note(getNoteName(el), getNoteContent(el));
                n.setParent(filesystem.getTagFolders().get(parentName));
                filesystem.getNotes().put(getNoteName(el), n);
                List<Note> parentsNote = filesystem.getFolderToNotes().get(parentName);
                if (parentsNote == null) {
                    parentsNote = new ArrayList<>();
                }
                parentsNote.add(n);
                filesystem.getFolderToNotes().put(parentName, parentsNote);
            }
            if (part == TreePart.REMOVE) {
                final String elRm = el.substring(1);
                Note n = filesystem.getNotes().get(elRm);
                if (n != null) {
                    filesystem.getNotes().remove(elRm);
                } else {
                    TagFolder tf = filesystem.getTagFolders().get(elRm);
                    if (tf != null) {
                        TagFolder removee = filesystem.getTagFolders().remove(elRm);
                        removee.getParent().getChildren().remove(elRm);
                        removeChildrenTagsAndNotes(removee, filesystem);
                    } else {
                        System.out.println("I am not removing this -> " + el);
                    }
                }
            }
        }

        return filesystem;
    }

    private static void removeChildrenTagsAndNotes(TagFolder removee, Filesystem filesystem) {
        List<Note> childNotes =
                filesystem.getFolderToNotes().get(removee.getName()); // remove notes under removee
        if (childNotes != null) {
            childNotes.forEach(
                    note ->
                            filesystem
                                    .getNotes()
                                    .remove(note.getName())); // remove folders under removee
        }
        filesystem.getFolderToNotes().remove(removee.getName());
        filesystem.getTagFolders().remove(removee.getName());
        removee.getChildren()
                .forEach((key, value) -> removeChildrenTagsAndNotes(value, filesystem));
    }

    private static String getNoteContent(String el) {
        return el.split(":")[1];
    }

    private static String getNoteName(String el) {
        return el.split(":")[0];
    }

    private static TreePart getTreePart(String str) {
        if (str.contains("!")) {
            return TreePart.REMOVE;
        } else {
            if (str.contains(":")) return TreePart.NOTE;
            else if (str.equals("_")) return TreePart.END;
            else return TreePart.TAG;
        }
    }
}

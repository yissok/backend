/* (C)2024 */
package com.example.demo.unit;

import com.example.demo.model.Filesystem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

class S3ServiceTest {
    String serialisedTreeFile = """
ROOT-Nintendo-Smash-_-Zelda-Link-Sword:iamasword
""";

    @Test
    void testInit() throws IOException {
        Filesystem filesystem = Filesystem.createTree(serialisedTreeFile, null);
        System.out.println("hello");
    }



    @Test
    void testSerialisation() throws IOException {
        Filesystem filesystem = Filesystem.createTree(serialisedTreeFile, null);
        System.out.println("hello");
        String generatedSerialisedTree = Filesystem.serialiseTree(filesystem);
        assertEquals(serialisedTreeFile, generatedSerialisedTree);
    }

    String serialisedAddition =
            """
ROOT-Nintendo-Zelda-Wolf-Shield:iamashield-_-_-_-Xbox-_-Playstation
""";

    @Test
    void testAdding() {
        Filesystem filesystem = Filesystem.createTree(serialisedTreeFile, null);
        System.out.println("hello");
        Filesystem filesystem2 = Filesystem.createTree(serialisedAddition, filesystem);
        System.out.println("hello");
    }

    String serialisedDeletion = """
ROOT-Nintendo-!Zelda-_-!Xbox
""";

    @Test
    void testRemoving() {

        Filesystem filesystem = Filesystem.createTree(serialisedTreeFile, null);
        System.out.println("hello");
        Filesystem filesystem2 = Filesystem.createTree(serialisedAddition, filesystem);
        System.out.println("hello");
        Filesystem filesystem3 = Filesystem.createTree(serialisedDeletion, filesystem2);
        System.out.println("hello");
    }

    String serialisedDeletionAndAddition =
            """
ROOT-!Nintendo-Nvidia-chip-_-!chip-Apple-iphone-6-_-7-_-8-_-9-iphone9news-_-_-!9-X-11-12-13-14-15
""";

    @Test
    void testAddingAndRemoving() {}

    @Test
    void testInputChangeSetsOutputCompressedTree() {}

    @AfterAll
    @BeforeAll
    static void padLogs() {

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}

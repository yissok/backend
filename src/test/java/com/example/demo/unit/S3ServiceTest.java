package com.example.demo.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.model.Filesystem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class S3ServiceTest {
    String serialisedTreeFile = "ROOT-Nintendo-Smash-_-Zelda-Link-Sword:iamasword-_-_-_-_-";

    @Test
    void testSerialisation() throws IOException {
        Filesystem filesystem = Filesystem.createTree(serialisedTreeFile, null);
        String generatedSerialisedTree = Filesystem.serialiseTree(filesystem);
        assertEquals(serialisedTreeFile, generatedSerialisedTree);
    }

    String serialisedAddition =
            "ROOT-Nintendo-Zelda-Wolf-Shield:iamashield-_-_-_-Xbox-_-Playstation";

    @Test
    void testAdding() {
        Filesystem filesystem = Filesystem.createTree(serialisedTreeFile, null);
        Filesystem filesystem2 = Filesystem.createTree(serialisedAddition, filesystem);
        String generatedSerialisedTree = Filesystem.serialiseTree(filesystem2);
        assertEquals(
                "ROOT-Nintendo-Smash-_-Zelda-Wolf-Shield:iamashield-_-Link-Sword:iamasword-_-_-_-Xbox-_-Playstation-_-_-",
                generatedSerialisedTree);
    }

    String serialisedDeletion = "ROOT-Nintendo-!Zelda-_-!Xbox";

    @Test
    void testRemoving() {

        Filesystem filesystem = Filesystem.createTree(serialisedTreeFile, null);
        Filesystem filesystem2 = Filesystem.createTree(serialisedAddition, filesystem);
        Filesystem filesystem3 = Filesystem.createTree(serialisedDeletion, filesystem2);
        String generatedSerialisedTree = Filesystem.serialiseTree(filesystem3);
        assertEquals("ROOT-Nintendo-Smash-_-_-Playstation-_-_-", generatedSerialisedTree);
    }

    String serialisedDeletionAndAddition =
            "ROOT-!Nintendo-Nvidia-chip-_-!chip-_-Apple-iphone-6-_-7-_-8-_-9-iphone9news-_-_-!9-X-_-11-_-12-_-13-_-14-_-15";

    @Test
    void testAddingAndRemoving() {
        Filesystem filesystem = Filesystem.createTree(serialisedDeletionAndAddition, null);
        assertTrue(
                filesystem
                        .getTagFolders()
                        .get(Filesystem.LB_ROOT)
                        .getChildren()
                        .containsKey("Nvidia"));
        assertTrue(
                filesystem
                        .getTagFolders()
                        .get(Filesystem.LB_ROOT)
                        .getChildren()
                        .containsKey("Apple"));
        assertTrue(
                filesystem
                        .getTagFolders()
                        .get(Filesystem.LB_ROOT)
                        .getChildren()
                        .get("Apple")
                        .getChildren()
                        .containsKey("iphone"));
        assertTrue(
                filesystem
                        .getTagFolders()
                        .get(Filesystem.LB_ROOT)
                        .getChildren()
                        .get("Apple")
                        .getChildren()
                        .get("iphone")
                        .getChildren()
                        .containsKey("6"));
        assertTrue(
                filesystem
                        .getTagFolders()
                        .get(Filesystem.LB_ROOT)
                        .getChildren()
                        .get("Apple")
                        .getChildren()
                        .get("iphone")
                        .getChildren()
                        .containsKey("7"));
        assertTrue(
                filesystem
                        .getTagFolders()
                        .get(Filesystem.LB_ROOT)
                        .getChildren()
                        .get("Apple")
                        .getChildren()
                        .get("iphone")
                        .getChildren()
                        .containsKey("8"));
        assertTrue(
                filesystem
                        .getTagFolders()
                        .get(Filesystem.LB_ROOT)
                        .getChildren()
                        .get("Apple")
                        .getChildren()
                        .get("iphone")
                        .getChildren()
                        .containsKey("X"));
        assertTrue(
                filesystem
                        .getTagFolders()
                        .get(Filesystem.LB_ROOT)
                        .getChildren()
                        .get("Apple")
                        .getChildren()
                        .get("iphone")
                        .getChildren()
                        .containsKey("11"));
        assertTrue(
                filesystem
                        .getTagFolders()
                        .get(Filesystem.LB_ROOT)
                        .getChildren()
                        .get("Apple")
                        .getChildren()
                        .get("iphone")
                        .getChildren()
                        .containsKey("12"));
        assertTrue(
                filesystem
                        .getTagFolders()
                        .get(Filesystem.LB_ROOT)
                        .getChildren()
                        .get("Apple")
                        .getChildren()
                        .get("iphone")
                        .getChildren()
                        .containsKey("13"));
        assertTrue(
                filesystem
                        .getTagFolders()
                        .get(Filesystem.LB_ROOT)
                        .getChildren()
                        .get("Apple")
                        .getChildren()
                        .get("iphone")
                        .getChildren()
                        .containsKey("14"));
        assertTrue(
                filesystem
                        .getTagFolders()
                        .get(Filesystem.LB_ROOT)
                        .getChildren()
                        .get("Apple")
                        .getChildren()
                        .get("iphone")
                        .getChildren()
                        .containsKey("15"));
    }

    @Test
    void testInputChangeSetsOutputCompressedTree() {}

    @AfterAll
    @BeforeAll
    static void padLogs() {

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}

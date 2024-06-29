package com.example.demo.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.model.Filesystem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FilesystemTest {
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
                "ROOT-Nintendo-Smash-_-Zelda-Link-Sword:iamasword-_-Wolf-Shield:iamashield-_-_-_-Playstation-_-Xbox-_-_-",
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
    //    here ___|               here ___|                                          here ___|

    String serialisedDeletionAndAdditionResult =
            "ROOT-Apple-iphone-11-_-12-_-13-_-14-_-15-_-6-_-7-_-8-_-X-_-_-_-Nvidia-_-_-";

    @Test
    void testSerialisedStringWhereRemovalOccursWithinString() {
        Filesystem filesystem = Filesystem.createTree(serialisedDeletionAndAddition, null);
        String generatedSerialisedTree = Filesystem.serialiseTree(filesystem);
        assertEquals(serialisedDeletionAndAdditionResult, generatedSerialisedTree);
    }

    @Test
    void testInputChangeSetsOutputCompressedTree() {}

    @AfterAll
    @BeforeAll
    static void padLogs() {

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}

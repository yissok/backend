/* (C)2024 */
package com.example.demo.unit;

import com.example.demo.dto.YamlDeserializer;
import com.example.demo.model.Filesystem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class S3ServiceTest {
    String dbFile =
            """
nodes:
  - name: nintendo
    parent: root
    content: nil
    children:
      - zelda
      - smash
  - name: zelda
    parent: nintendo
    content: nil
    children:
      - link
  - name: smash
    parent: nintendo
    content: nil
    children: []
  - name: link
    parent: zelda
    content: nil
    children:
      - sword
  - name: sword
    parent: link
    content: "This is the content of the sword file."
    children: nil
""";

    @Test
    void testAddingNode() throws IOException {
        Filesystem backupImport = new Filesystem(dbFile);
        Filesystem filesystem = YamlDeserializer.deserializeYaml(dbFile);
        System.out.println("hello");
    }

    @Test
    void testRemovingNode() {}

    @AfterAll
    @BeforeAll
    static void stuff() {

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}

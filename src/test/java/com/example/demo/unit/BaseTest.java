package com.example.demo.unit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public interface BaseTest {

    @AfterAll
    @BeforeAll
    static void padLogs() {

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}

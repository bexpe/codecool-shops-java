package com.codecool.shop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by rybeusz on 30.05.17.
 */
class ApplicationTest {
    @BeforeEach
    void setUp() {
        String[] args = {};
        Application.run(args);
    }

    @Test
    void testGetApp() {
        assertNotNull(Application.getApp());
    }

    @Test
    void testGetConnector() {
        assertNotNull(Application.getApp().getConnector());
    }

}
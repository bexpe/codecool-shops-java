package com.codecool.shop;

import com.codecool.shop.dao.SQLiteJDBCConnector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.management.InstanceAlreadyExistsException;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Created by rybeusz on 30.05.17.
 */
class ApplicationTest {
    @BeforeEach
    void setUp() {
        String[] args = {};
        try {
            Application.run(args);
        } catch (Exception e) {
        }
    }

    @Test
    void testGetApp() {
        assertNotNull(Application.getApp());
    }

    @Test
    void testGetConnector() {
        assertNotNull(Application.getApp().getConnector());
    }

    @Test
    void testApplicationThrowErrorIfAlreadyExist() {
        String[] args = {};
        assertThrows(InstanceAlreadyExistsException.class, ()-> {
            Application.run(args);
        });
    }

    @Mock
    private SQLiteJDBCConnector connector;

    @Test
    void testApplicationThrowErrorIfMissingDatabaseTables() {
        String[] args = {};
        MockitoAnnotations.initMocks(this);
        connector = new SQLiteJDBCConnector();
        connector.setDatabaseFilePath("jdbc:sqlite:src/main/resources/test_database.db");
        assertThrows(NoSuchElementException.class, ()-> {
            Application app = new Application(connector);
            app.run(args);
        });
    }

}
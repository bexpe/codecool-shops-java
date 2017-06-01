package com.codecool.shop;

import com.codecool.shop.dao.SQLiteJDBCConnector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by rybeusz on 30.05.17.
 */
class ApplicationTest {
    private Application app;
//
    @Mock
    private SQLiteJDBCConnector connector;
//
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        connector = new SQLiteJDBCConnector();
        connector.setDatabaseFilePath("jdbc:sqlite:src/main/resources/test_database.db");
        app = new Application(connector);
    }

    @AfterEach
    void closeDb() {
        try {
            connector.getConnection().close();
        } catch (SQLException e) {
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
    void testApplicationThrowErrorIfMissingDatabaseTables() {
        String[] args = {};
        app.getConnector().dropTables();

        assertThrows(NoSuchElementException.class, ()-> {
            app.run(args);
        });
    }

}
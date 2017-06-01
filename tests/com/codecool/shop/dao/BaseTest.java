package com.codecool.shop.dao;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

abstract class BaseTest {
    @Mock
    SQLiteJDBCConnector connector;

    private SQLFilesPaths sqlFilesPaths;

    void setUpDB() throws SQLException {
        MockitoAnnotations.initMocks(this);
        connector = new SQLiteJDBCConnector();
        connector.setDatabaseFilePath("jdbc:sqlite:tests/resources/test_database.db");
        connector.setSqlFiles(sqlFilesPaths);
        connector.connectToDb();
        connector.dropTables();
        connector.createTables();
        connector.fillTables();
    }

    void closeDB() throws SQLException {
        connector.getConnection().close();
    }
}

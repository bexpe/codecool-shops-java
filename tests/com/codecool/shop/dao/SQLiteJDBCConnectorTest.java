package com.codecool.shop.dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SQLiteJDBCConnectorTest {

    @Test
    void testConnectingToDBWhenPathIsIncorrect() throws SQLException {
        SQLiteJDBCConnector connector = new SQLiteJDBCConnector();
        connector.setDatabaseFilePath("jdbc:sqlite:tests/wrong_directory/resources/test_database.db");
        assertThrows(RuntimeException.class, connector::connectToDb);
    }
}
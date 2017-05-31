package com.codecool.shop.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

class ProductCategoryDaoSqliteTest {

    private ProductCategoryDao productCategoryDao;
    private SQLiteJDBCConnector connector;

    @BeforeEach
    void setUp() throws SQLException {
        connector = new SQLiteJDBCConnector();
        connector.setDatabaseFilePath("jdbc:sqlite:src/main/resources/test_database.db");
        connector.connectToDb();
        connector.dropTables();
        connector.createTables();
        connector.fillTables();
        productCategoryDao = new ProductCategoryDaoSqlite(connector.getConnection());
    }

    @AfterEach
    void close() throws SQLException {
        connector.getConnection().close();
    }
}
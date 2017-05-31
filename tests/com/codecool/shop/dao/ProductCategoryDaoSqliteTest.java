package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void testFindCategoryById() {
        ProductCategory category = new ProductCategory(1, "chemia", "niemieckie środki czystości i nie tylko", "Berlin");
        assertEquals(category, productCategoryDao.find(-1));
    }
}
package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductCategoryDaoSqliteTest {

    @Spy
    private List<ProductCategory> spiedCategories = new ArrayList<>();
    @Mock
    private ProductCategoryDao productCategoryDao;
    @Mock
    private SQLiteJDBCConnector connector;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
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
        assertEquals(category.getDepartment(), productCategoryDao.find(1).getDepartment());
    }

    @Test
    void testFindAllCategories() {
        List<ProductCategory> categories = productCategoryDao.getAll();
        assertEquals(
                Arrays.asList(
                    new ProductCategory(1,"chemia", "niemieckie środki czystości i nie tylko", "Berlin"),
                    new ProductCategory(2,"spożywcze", "tylko świeże warzywa i owoce", "Hala Targowa"),
                    new ProductCategory(3,"narzędzia", "narzędzia sprawne jak szwajcarskie zegarki", "neutralna Szwajcaria"),
                    new ProductCategory(4,"słodycze", "wszystko to co misie lubią najbardziej", "Dom Kubusia Puchatka")
                ).get(2).getDepartment(),
                categories.get(2).getDepartment());
    }
}
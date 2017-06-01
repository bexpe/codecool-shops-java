package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        ProductCategory category = mock(ProductCategory.class);
        when(category.toString()).thenReturn(
                "id: 1,name: chemia, department: Berlin, " +
                        "description: niemieckie środki czystości i nie tylko");
        assertEquals(category.toString(), productCategoryDao.find(1).toString());
    }

    @Test
    void testGetAllCategoriesListSize() {
        Mockito.doReturn(4).when(spiedCategories).size();
        assertEquals(spiedCategories.size(), productCategoryDao.getAll().size());
    }

    @Test
    void testGetCategoryFromAllCategoriesList() {
        ProductCategory productCategory = mock(ProductCategory.class);
        List<ProductCategory> spiedCategories = Mockito.spy(new ArrayList<>());

        spiedCategories.add(productCategory);
        Mockito.verify(spiedCategories).add(productCategory);

        Mockito.doReturn(productCategory).when(spiedCategories).get(2);
        when(spiedCategories.get(2).toString()).thenReturn(
                "id: 3,name: narzędzia, department: neutralna Szwajcaria, " +
                        "description: narzędzia sprawne jak szwajcarskie zegarki");

        assertEquals(
                spiedCategories.get(2).toString(),
                productCategoryDao.getAll().get(2).toString()
        );
    }
}
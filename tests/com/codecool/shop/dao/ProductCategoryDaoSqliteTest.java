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
import static org.mockito.Mockito.*;

class ProductCategoryDaoSqliteTest {

    private SQLFilesPaths sqlFilesPaths;
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
        connector.setDatabaseFilePath("jdbc:sqlite:tests/resources/test_database.db");
        connector.setSqlFiles(sqlFilesPaths);
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
                "id: 1,name: TEST CATEGORY 1, department: DEPARTMENT 1, description: DESCRIPTION 1");
        assertEquals(category.toString(), productCategoryDao.find(1).toString());
    }

    @Test
    void testGetAllCategoriesListSize() {
        doReturn(2).when(spiedCategories).size();
        assertEquals(spiedCategories.size(), productCategoryDao.getAll().size());
    }

    @Test
    void testGetCategoryFromAllCategoriesList() {
        ProductCategory productCategory = mock(ProductCategory.class);
        List<ProductCategory> spiedCategories = Mockito.spy(new ArrayList<>());

        spiedCategories.add(productCategory);
        verify(spiedCategories).add(productCategory);

        doReturn(productCategory).when(spiedCategories).get(1);
        when(spiedCategories.get(1).toString()).thenReturn(
                "id: 2,name: TEST CATEGORY 2, department: DEPARTMENT 2, description: DESCRIPTION 2");

        assertEquals(
                spiedCategories.get(1).toString(),
                productCategoryDao.getAll().get(1).toString()
        );
    }
}
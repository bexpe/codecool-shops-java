package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductDaoSqliteTest extends BaseTest {

    @Mock
    private ProductDao productDao;
    @Mock
    private SupplierDao supplierDao;
    @Mock
    private ProductCategoryDao productCategoryDao;

    @BeforeEach
    void setUp() throws SQLException {
        super.setUpDB();
        productDao = new ProductDaoSqlite(connector.getConnection(),
                new SupplierDaoSqlite(connector.getConnection()),
                new ProductCategoryDaoSqlite(connector.getConnection()));
    }

    @AfterEach
    void tearDown() throws SQLException {
        super.closeDB();
    }

    @Test
    void testFindProductById() {
        Product product = mock(Product.class);
        when(product.getDescription()).thenReturn("DESCRIPTION 2");
        assertEquals(
                product.getDescription(),
                productDao.find(2).getDescription()
        );
    }
}
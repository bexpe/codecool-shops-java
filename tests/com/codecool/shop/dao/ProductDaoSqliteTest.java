package com.codecool.shop.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import java.sql.SQLException;

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

}
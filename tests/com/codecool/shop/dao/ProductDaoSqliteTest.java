package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductDaoSqliteTest extends BaseTest {

    @Spy
    private List<Product> spiedProducts = new ArrayList<>();
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

    @Test
    void testGetAllProductListSize() {
        doReturn(4).when(spiedProducts).size();
        assertEquals(spiedProducts.size(), productDao.getAll().size());
    }
}
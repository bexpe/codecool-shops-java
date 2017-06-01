package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    void testProductDaoSqliteImplementProductDao() {
        assertTrue(ProductDao.class.isAssignableFrom(productDao.getClass()));
    }

    @Test
    void testAddProductToDB() {
        Supplier supplier = mock(Supplier.class);
        when(supplier.getId()).thenReturn(1);
        ProductCategory category = mock(ProductCategory.class);
        when(category.getId()).thenReturn(1);
        Product product = new Product(
                "TEST PRODUCT",
                1234f,
                "PLN",
                "TEST DESCRIPTION",
                category,
                supplier
        );
        assertEquals(1, (int) productDao.add(product));
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

    @Test
    void testGetProductFromAllProductList() {
        Product product = mock(Product.class);
        spiedProducts = spy(new ArrayList<Product>());
        spiedProducts.add(product);
        verify(spiedProducts).add(product);
        doReturn(product).when(spiedProducts).get(3);
        when(spiedProducts.get(3).toString()).thenReturn(
                "id: 4, " +
                        "name: PRODUCT 4, " +
                        "defaultPrice: 104.000000, " +
                        "defaultCurrency: PLN, " +
                        "productCategory: TEST CATEGORY 1, " +
                        "supplier: SUPPLIER 1"
        );
        assertEquals(spiedProducts.get(3).toString(), productDao.getAll().get(3).toString());
    }

    @Test
    void testGetProductBySupplierListSize() {
        Supplier supplier = mock(Supplier.class);
        when(supplier.getId()).thenReturn(1);
        doReturn(4).when(spiedProducts).size();
        assertEquals(spiedProducts.size(), productDao.getBy(supplier).size());
    }

    @Test
    void testGetProductByCategoryListSize() {
        ProductCategory mockedCategory = mock(ProductCategory.class);
        when(mockedCategory.getId()).thenReturn(1);
        doReturn(4).when(spiedProducts).size();
        assertEquals(spiedProducts.size(), productDao.getBy(mockedCategory).size());
    }

    @Test
    void testGetProductByName() {
        when(spiedProducts.size()).thenReturn(1);
        assertEquals(spiedProducts.size(), productDao.getBy("PRODUCT 4").size());
    }
}
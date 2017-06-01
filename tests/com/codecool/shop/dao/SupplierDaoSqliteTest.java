package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SupplierDaoSqliteTest extends BaseTest{

    @Spy
    private List<Supplier> spiedSuppliers = new ArrayList<>();
    @Mock
    private SupplierDao supplierDao;

    @BeforeEach
    void setUp() throws SQLException {
        super.setUpDB();
        supplierDao = new SupplierDaoSqlite(connector.getConnection());
    }

    @AfterEach
    void tearDown() throws SQLException {
        super.closeDB();
    }

    @Test
    void testFindSupplierById() {
        Supplier supplier = new Supplier(1, "SUPPLIER 1", "DESCRIPTION SUPPLIER 1");
        assertEquals(supplier.getDescription(), supplierDao.find(1).getDescription());
    }

    @Test
    void testGetAllSuppliersListSize() {
        doReturn(2).when(spiedSuppliers).size();
        assertEquals(spiedSuppliers.size(), supplierDao.getAll().size());
    }

    @Test
    void testGetSupplierFromAllSuppliersList() {
        Supplier supplier = mock(Supplier.class);
        List<Supplier> spiedSuppliers = Mockito.spy(new ArrayList<>());

        spiedSuppliers.add(supplier);
        verify(spiedSuppliers).add(supplier);

        doReturn(supplier).when(spiedSuppliers).get(1);
        when(spiedSuppliers.get(1).getDescription()).thenReturn("DESCRIPTION SUPPLIER 2");

        assertEquals(spiedSuppliers.get(1).getDescription(),
                supplierDao.getAll().get(1).getDescription()
        );
    }
}
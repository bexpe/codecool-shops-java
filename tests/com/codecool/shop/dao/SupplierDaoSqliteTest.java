package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
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

class SupplierDaoSqliteTest {
    @Spy
    private List<Supplier> spiedSuppliers = new ArrayList<>();
    @Mock
    private SQLiteJDBCConnector connector;
    @Mock
    private SupplierDao supplierDao;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        connector = new SQLiteJDBCConnector();
        connector.setDatabaseFilePath("jdbc:sqlite:tests/resources/test_database.db");
        connector.connectToDb();
        connector.dropTables();
        connector.createTables();
        connector.fillTables();
        supplierDao = new SupplierDaoSqlite(connector.getConnection());
    }

    @AfterEach
    void close() throws SQLException {
        connector.getConnection().close();
    }

    @Test
    void testFindSupplierById() {
        Supplier supplier = new Supplier(1, "Ariel", "Najczerwieńsza jakość");
        assertEquals(supplier.getDescription(), supplierDao.find(1).getDescription());
    }

    @Test
    void testGetAllSuppliersListSize() {
        Mockito.doReturn(8).when(spiedSuppliers).size();
        assertEquals(spiedSuppliers.size(), supplierDao.getAll().size());
    }

    @Test
    void testGetSupplierFromAllSuppliersList() {
        Supplier supplier = mock(Supplier.class);
        List<Supplier> spiedSuppliers = Mockito.spy(new ArrayList<>());
        spiedSuppliers.add(supplier);
        Mockito.verify(spiedSuppliers).add(supplier);
        Mockito.doReturn(supplier).when(spiedSuppliers).get(6);
        when(spiedSuppliers.get(6).getDescription()).thenReturn("Twój czas należy do nas");
        assertEquals(spiedSuppliers.get(6).getDescription(),
                supplierDao.getAll().get(6).getDescription()
        );
    }
}
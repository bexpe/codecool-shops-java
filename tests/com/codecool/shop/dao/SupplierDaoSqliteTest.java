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

class SupplierDaoSqliteTest {
    @Mock
    private SQLiteJDBCConnector connector;
    @Mock
    private SupplierDao supplierDao;
    @Spy
    List<Supplier> spiedSuppliers =  new ArrayList<>();

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        connector = new SQLiteJDBCConnector();
        connector.setDatabaseFilePath("jdbc:sqlite:src/main/resources/test_database.db");
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
    void testFindAllSuppliers() {
        Mockito.doReturn(8).when(spiedSuppliers).size();
        assertEquals(spiedSuppliers.size(), supplierDao.getAll().size());
    }
}
package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplierDaoSqliteTest {
    private SQLiteJDBCConnector connector;
    private SupplierDao supplierDao;

    @BeforeEach
    void setUp() throws SQLException {
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
}
package com.codecool.shop.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

class ProductDaoSqliteTest extends BaseTest {
    @BeforeEach
    void setUp() throws SQLException {
        super.setUpDB();
    }

    @AfterEach
    void tearDown() throws SQLException {
        super.closeDB();
    }

}
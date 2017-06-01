package com.codecool.shop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierTest {

    @Test
    void testToString() {
        Supplier supplier = new Supplier(1, "name", "description");
        assertEquals(
                "id: 1, " +
                "name: name, " +
                "description: description",
                supplier.toString());
    }

}
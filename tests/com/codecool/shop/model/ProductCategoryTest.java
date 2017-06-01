package com.codecool.shop.model;

import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by beata on 29.05.17.
 */
class ProductCategoryTest {

    @Test
    void testToString() {
        ProductCategory productCategory = new ProductCategory(
                1,
                "name",
                "description",
                "department");

        assertEquals(
                "id: 1," +
                        "name: name, " +
                        "department: department, " +
                        "description: description",
                productCategory.toString());
    }

}
package com.codecool.shop.dao;

public enum SQLFilesPaths {
    PRODUCTS_DATA("tests/resources/sql/productsData.sql"),
    PRODUCTS("tests/resources/sql/products.sql"),
    CATEGORIES_DATA("tests/resources/sql/categoriesData.sql"),
    CATEGORIES("tests/resources/sql/categories.sql"),
    SUPPLIERS_DATA("tests/resources/sql/suppliersData.sql"),
    SUPPLIERS("tests/resources/sql/suppliers.sql");

    private final String path;

    SQLFilesPaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    @Override
    public String toString() {
        return path;
    }
}

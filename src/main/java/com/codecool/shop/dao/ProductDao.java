package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public interface ProductDao {

    Integer add(Product product);

    Product find(int id);

    List<Product> getAll();

    List<Product> getBy(Supplier supplier);

    List<Product> getBy(ProductCategory productCategory);

    List<Product> getBy(String name);
}

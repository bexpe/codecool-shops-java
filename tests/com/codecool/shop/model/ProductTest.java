package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProductTest {
	private Product product;
	private ProductCategory productCategory;
	private Supplier supplier;

	@BeforeEach
	void setup() {
		productCategory = mock(ProductCategory.class);
		supplier = mock(Supplier.class);
		product = new Product(
				1,
				"name",
				11.1f,
				"CUR",
				"description",
				productCategory,
				supplier);
	}

	@Test
	void testGetDefaultPrice() {
		assertEquals(11.1f, product.getDefaultPrice());
	}

	@Test
	void testGetDefaultCurrency() {
		assertEquals("CUR", product.getDefaultCurrency());
	}

	@Test
	void testGetPrice() {
		assertEquals("11.1" + " " + "CUR", product.getPrice());
	}
}
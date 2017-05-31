package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
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
}
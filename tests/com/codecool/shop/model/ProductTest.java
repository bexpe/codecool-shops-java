package com.codecool.shop.model;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductTest {
	private Product product;
	private Product productWithNoId;
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

		productWithNoId = new Product(
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

	@Test
	void testGetProductCategory() {
		assertEquals(productCategory, product.getProductCategory());
	}

	@Test
	void testGetProductCategoryWithNegativePrice () {
		assertThrows(IllegalArgumentException.class, ()->{
			product = new Product(
					1,
					"name",
					-11.1f,
					"CUR",
					"description",
					productCategory,
					supplier);
		});
	}

	@Test
	void testGetSupplier() {
		assertEquals(supplier, product.getSupplier());
	}

	@Test
	void testSetSupplier() {
		Supplier setNewSupplier = mock(Supplier.class);
		product.setSupplier(setNewSupplier);
		assertEquals(setNewSupplier, product.getSupplier());
	}

	@Test
	void testToString() {
		when(productCategory.getName()).thenReturn("mock category");
		when(supplier.getName()).thenReturn("mock supplier");
		assertEquals(
				"id: 1, " +
						"name: name, " +
						"defaultPrice: 11.1, " +
						"defaultCurrency: CUR, " +
						"productCategory: mock category, " +
						"supplier: mock supplier",
				product.toString());

		assertEquals(
				"id: 0, " +
				"name: name, " +
				"defaultPrice: 11.1, " +
				"defaultCurrency: CUR, " +
				"productCategory: mock category, " +
				"supplier: mock supplier",
                productWithNoId.toString());
	}
}
package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Created by beata on 29.05.17.
 */
class BasketItemTest {
	private Product product;
	private ProductCategory productCategory;
	private Supplier supplier;
	private BasketItem basketItem;

	@BeforeEach
	void setup() {
		product = mock(Product.class);
		productCategory = mock(ProductCategory.class);
		supplier = mock(Supplier.class);
		basketItem = new BasketItem (
				product,
				11);
	}

	@Test
	@DisplayName("Returns valid product object")
	public void testGetSupplierObject() {
		assertEquals(product, basketItem.getProduct());
	}

	@Test
	@DisplayName("Sets valid product object")
	void testSetProductObject() {
		Product setNewProduct = mock(Product.class);
		basketItem.setProduct(setNewProduct);
		assertEquals(setNewProduct, basketItem.getProduct());
	}
}
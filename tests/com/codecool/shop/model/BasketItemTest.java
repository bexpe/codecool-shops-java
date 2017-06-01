package com.codecool.shop.model;

import com.codecool.shop.model.BasketItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
	void testGetProductObject() {
		assertEquals(product, basketItem.getProduct());
	}

	@Test
	@DisplayName("Sets valid product object")
	void testSetProductObject() {
		Product setNewProduct = mock(Product.class);
		basketItem.setProduct(setNewProduct);
		assertEquals(setNewProduct, basketItem.getProduct());
	}

	@Test
	@DisplayName("Returns valid quantity integer")
	void testGetQuantityInteger() {
		assertEquals(11, (int) basketItem.getQuantity());
	}

	@Test
	@DisplayName("Returns valid quantity integer")
	void testSetQuantityInteger() {
		basketItem.setQuantity(15);
		assertEquals(15, (int) basketItem.getQuantity());
	}

	@Test
	void testGetValue() {
		when (product.getDefaultPrice()).thenReturn(100f);
		assertEquals(1100f, basketItem.getValue());// because quantity is 11
	}
}
package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Currency;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by beata on 29.05.17.
 */
class ProductTest {
	public ProductCategory testCategory;
	public Supplier testSupplier;
	public Product testProduct;
	public Currency testCurrency;

	@BeforeEach
	void setup() {
		testCategory = new ProductCategory("alkohol", "niskoprocentowy", "monopolowy");
		testSupplier = new Supplier("Warka", "slaba marka");
		testProduct = new Product("piwo", 8, "PLN", "z pianka", testCategory, testSupplier);
	}

	@Test
	@DisplayName("Sets and returns valid default price of a Product")
	public void testSetAndGetDefaultPrice()
	{
		testProduct.setDefaultPrice(12);
		assertEquals(12, testProduct.getDefaultPrice());
	}

	@Test
	@DisplayName("Don't allow to set default price to minus value")
	public void testSetDefaultPriceToMinus()
	{
		assertThrows(IllegalArgumentException.class, ()->
				testProduct.setPrice(-10, "PLN"));
	}

	@Test
	@DisplayName("Sets and returns valid currency of a Product")
	public void testSetAndGetDefaultCurrency()
	{
		testCurrency = Currency.getInstance(Locale.UK);
		testProduct.setDefaultCurrency(testCurrency);
		assertEquals(Currency.getInstance(Locale.UK), testProduct.getDefaultCurrency());
	}

	@Test
	@DisplayName("Sets and returns valid price of a Product as a double type")
	public void testSetAndGetPriceAsDoubleType()
	{
		testCurrency = Currency.getInstance(Locale.UK);
		testProduct.setPrice(12, "PLN");
		assertEquals("12.0 PLN", testProduct.getPrice());
	}

	@Test
	@DisplayName("Sets and returns valid product category object")
	public void testSetAndGetCategoryObject()
	{
		testProduct.setProductCategory(testCategory);
		assertEquals("id: 0,name: alkohol, department: monopolowy, description: niskoprocentowy", testProduct.getProductCategory().toString());
	}

	@Test
	@DisplayName("Sets and returns valid supplier object")
	public void testSetAndGetSupplierObject()
	{
		testProduct.setSupplier(testSupplier);
		assertEquals("id: 0, name: Warka, description: slaba marka", testProduct.getSupplier().toString());
	}

	@Test
	@DisplayName("Returns valid string descripting a product")
	void testToStringOfProduct () {

		testProduct.toString();
		assertEquals("id: 0, name: piwo, defaultPrice: 8.0, defaultCurrency: PLN, productCategory: alkohol, supplier: Warka", testProduct.toString());
	}
}
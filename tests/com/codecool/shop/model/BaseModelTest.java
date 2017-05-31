package com.codecool.shop.model;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by beata on 29.05.17.
 */
class BaseModelTest {
	@Test
	@DisplayName("Sets valid id and returns valid id of a Product")
	public void testSetAndGetValidId()
	{
		BaseModel testObject = new BaseModel("piwo", "z pianka");
		testObject.setId(1);
		assertEquals(1, testObject.getId());
	}

	@Test
	@DisplayName("Tests whether calling setId with id LT 0 throws a IllegalArgumentException")
	public void testSetIdLT0() {
		BaseModel testObject = new BaseModel("piwo", "z pianka");
		assertThrows(IllegalArgumentException.class, ()-> testObject.setId(-1));
	}

	@Test
	@DisplayName("Sets valid id and returns valid id of a Product")
	public void testSetAndGetValidName()
	{
		BaseModel testObject = new BaseModel("piwo", "z pianka");
		testObject.setName("piwko");
		assertEquals("piwko", testObject.getName());
	}

	@Test
	@DisplayName("Sets valid description of a Product")
	void testSetAndGetValidDescription() {
		BaseModel testObject = new BaseModel("piwo", "z pianka");
		testObject.setDescription("z pianka");
		assertEquals("z pianka", testObject.getDescription());
	}

	@Test
	@DisplayName("Returns valid string descripting a BaseModel")
	void testToStringOfBaseModel () {
		BaseModel testObject = new BaseModel("piwo", "z pianka");
		testObject.toString();
		assertEquals("id:0,name:piwo,description:z pianka,", testObject.toString());
	}
}
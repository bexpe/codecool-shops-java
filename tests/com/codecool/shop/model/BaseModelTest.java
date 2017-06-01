package com.codecool.shop.model;

import static org.mockito.Mockito.*;

import com.codecool.shop.model.BaseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by beata on 29.05.17.
 */
class BaseModelTest {

	@Test
	@DisplayName("Gets valid id of a Product")
	void testGetValidId() {
		BaseModel testObject = new BaseModel("piwo", "z pianka");
		assertEquals(0, testObject.getId());
	}

	@Test
	@DisplayName("Sets and returns valid name of a Product")
	void testSetAndGetValidName() {
		BaseModel testObject = new BaseModel("piwo", "z pianka");
		testObject.setName("piwko");
		assertEquals("piwko", testObject.getName());
	}

	@Test
	@DisplayName("Sets and returns valid description of a Product")
	void testSetAndGetValidDescription() {
		BaseModel testObject = new BaseModel("piwo", "z pianka");
		testObject.setDescription("z pianka");
		assertEquals("z pianka", testObject.getDescription());
	}

	@Test
	@DisplayName("Returns valid string description of a BaseModel")
	void testToStringOfBaseModel () {
		BaseModel testObject = new BaseModel("piwo", "z pianka");
		testObject.toString();
		assertEquals("id:0,name:piwo,description:z pianka,", testObject.toString());
	}
}
package com.codecool.shop.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseModelTest {
    private BaseModel testObject;

	@Test
	@DisplayName("Gets valid id of a Product")
	void testGetValidId() {
        testObject = new BaseModel("piwo");
        assertEquals(0, testObject.getId());
        testObject = new BaseModel("piwo", "z pianka");
        assertEquals(0, testObject.getId());
		testObject = new BaseModel(2,"piwo", "z pianka");
		assertEquals(2, testObject.getId());
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
	void testToStringOfMethod () {
		BaseModel testObject = new BaseModel("piwo", "z pianka");
		assertEquals("id:0,name:piwo,description:z pianka,", testObject.toString());
	}
}
package com.qa.gorest.tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.StringUtils;

public class APISchemaValidationTest extends BaseTest {
	
	@BeforeMethod
	public void getUserSetUp() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test
	public void createUserAPISchemaTest() {
		User user = new User("Tom", StringUtils.getRandomEmail(), "male", "active");
		restClient.post(GOREST_ENDPOINT, "json", user, true, true).then().log().all().assertThat().statusCode(201)
				.body(matchesJsonSchemaInClasspath("createuserschema.json"));

	}

}

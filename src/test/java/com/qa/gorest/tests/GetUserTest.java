package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;

public class GetUserTest extends BaseTest {
	
	@BeforeMethod
	public void getUserSetUp() {
		restClient=new RestClient(prop,baseURI);
	}

	@Test
	public void getAllUsers() {
		restClient.get(GOREST_ENDPOINT, true,true).then().log().all().assertThat().statusCode(200);
	}
	
	@Test
	public void getUserTest() {
		restClient=new RestClient(prop,baseURI);
		restClient.get(GOREST_ENDPOINT+"/6935536", true,true).then().log().all().assertThat().statusCode(200)
		.and().body("id", equalTo(6935536));
	}
	
	@Test
	public void getUserWithQueryParamTest() {
		restClient=new RestClient(prop,baseURI);
		Map<String,Object> queryParamsMap=new HashMap<String,Object>();
		queryParamsMap.put("name", "naveen");
		queryParamsMap.put("status", "active");
		restClient.get(GOREST_ENDPOINT, null, queryParamsMap, true, true)
		.then().log().all().assertThat().statusCode(200);
	}

}

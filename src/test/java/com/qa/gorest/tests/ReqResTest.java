package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;

public class ReqResTest extends BaseTest {
	
	@BeforeMethod
	public void getUserSetUp() {
		restClient=new RestClient(prop,baseURI);
	}
	
	@Test
	public void getReqResTest() {
		restClient.get(REQRES_ENDPOINT+"/2", false,true).then().log().all().assertThat().statusCode(200);
	}


}

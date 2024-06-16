package com.qa.gorest.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AmadeusAPITest extends BaseTest {

	private String accessToken;

	@Parameters({ "grantType", "clientId", "clientSecret" })
	@BeforeMethod
	public void flightAPISetUp(String grantType, String clientID, String clientSecret) {
		restClient = new RestClient(prop, baseURI);
		accessToken = restClient.getAccessToken(AMADEUS_TOKEN_ENDPOINT, grantType, clientID, clientSecret);

	}

	@Test
	public void getFlightInfoTest() {

		RestClient restClientFlight = new RestClient(prop, baseURI);

		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		queryParamMap.put("origin", "PAR");
		queryParamMap.put("maxPrice", 200);

		Map<String, String> headersMap = new HashMap<String, String>();
		headersMap.put("Authorization", "Bearer " + accessToken);

		Response flightDataResponse = restClientFlight
				.get(AMADEUS_FLIGHTBOOKING_ENDPOINT, headersMap, queryParamMap, false, true).then().log().all()
				.assertThat().statusCode(200).and().extract().response();

		JsonPath js = flightDataResponse.jsonPath();
		String type = js.get("data[0].type");
		System.out.println(type);

	}

}

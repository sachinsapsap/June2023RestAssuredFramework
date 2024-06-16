package com.qa.gorest.tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIConstants;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.ExcelUtil;
import com.qa.gorest.utils.StringUtils;

public class CreateUserTest extends BaseTest {

	@BeforeMethod
	public void getUserSetUp() {
		restClient = new RestClient(prop, baseURI);
	}

	@DataProvider
	public Object[][] getUserData() {
		Object[][] data = { { "Subodh", "male", "active" }, { "Seema", "female", "inactive" },
				{ "Madhuri", "female", "active" } };
		return data;
	}

	@DataProvider
	public Object[][] getUserExcelSheetData() {
		return ExcelUtil.getTestData(APIConstants.GOREST_USER_SHEET_NAME);
	}

	@Test(dataProvider = "getUserExcelSheetData")
	public void createUserTest(String name, String gender, String status) {
		User user = new User(name, StringUtils.getRandomEmail(), gender, status);
		Integer userId = restClient.post(GOREST_ENDPOINT, "json", user, true, true).then().log().all().assertThat()
				.statusCode(201).extract().response().path("id");

		System.out.println(userId);

		RestClient restClientGet = new RestClient(prop, baseURI);

		restClientGet.get(GOREST_ENDPOINT + "/" + userId, true, true).then().log().all().assertThat().statusCode(200)
				.and().body("id", equalTo(userId));
	}

	

}

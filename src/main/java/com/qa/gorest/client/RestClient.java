package com.qa.gorest.client;

import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.Properties;

import com.qa.gorest.frameworkException.APIFrameworkException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

//	private static final String BASE_URI = "https://gorest.co.in";
//	private static final String BEARER_TOKEN = "e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6";

	private RequestSpecBuilder specBuilder;

	private Properties prop;
	private String baseURI;
	
	private boolean isAuthorizationHeaderAdded=false;

	public RestClient(Properties prop, String baseURI) {
		this.prop = prop;
		this.baseURI = baseURI;
		specBuilder = new RequestSpecBuilder();
	}

	public void addAuthorization() {
		if(!isAuthorizationHeaderAdded) {
		specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenId"));
		isAuthorizationHeaderAdded=true;
		}
	}

	private void setRequestContentType(String contentType) {
		switch (contentType.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;
		case "xml":
			specBuilder.setContentType(ContentType.XML);
			break;
		case "text":
			specBuilder.setContentType(ContentType.TEXT);
			break;
		case "multipart":
			specBuilder.setContentType(ContentType.MULTIPART);
			break;
		default:
			System.out.println("Please pass the correct content type");
			throw new APIFrameworkException("INVALIDCONTENTTYPE");
		}
	}

	private RequestSpecification createRequestSpec(boolean includeAuth) {

		specBuilder.setBaseUri(baseURI);

		if (includeAuth) {
			addAuthorization();
		}
		return specBuilder.build();

	}

	private RequestSpecification createRequestSpec(Map<String, String> headersMap, boolean includeAuth) {

		specBuilder.setBaseUri(baseURI);
		if (includeAuth) {
			addAuthorization();
		}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();

	}

	private RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, Object> queryParamMap, boolean includeAuth) {

		specBuilder.setBaseUri(baseURI);
		if (includeAuth) {
			addAuthorization();
		}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}

		if (queryParamMap != null) {
			specBuilder.addQueryParams(queryParamMap);
		}
		return specBuilder.build();

	}

	private RequestSpecification createRequestSpec(Object requestBody, String contentType, boolean includeAuth) {

		specBuilder.setBaseUri(baseURI);
		if (includeAuth) {
			addAuthorization();
		}
		setRequestContentType(contentType);
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();

	}

	private RequestSpecification createRequestSpec(Object requestBody, String contentType,
			Map<String, String> headersMap, boolean includeAuth) {

		specBuilder.setBaseUri(baseURI);
		if (includeAuth) {
			addAuthorization();
		}
		setRequestContentType(contentType);
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();

	}

//	Http methods Utils:

//	GET:

	public Response get(String serviceUrl, boolean includeAuth, boolean logRequired) {
		if (logRequired) {
			return RestAssured.given(createRequestSpec(includeAuth)).log().all().when().log().all().get(serviceUrl);
		} else {
			return RestAssured.given(createRequestSpec(includeAuth)).when().get(serviceUrl);
		}

	}

	public Response get(String serviceUrl, Map<String, String> headersMap, boolean logRequired,boolean includeAuth) {
		if (logRequired) {
			return RestAssured.given(createRequestSpec(headersMap,includeAuth)).log().all().when().log().all().get(serviceUrl);
		} else {
			return RestAssured.given(createRequestSpec(headersMap,includeAuth)).when().get(serviceUrl);
		}

	}

	public Response get(String serviceUrl, Map<String, String> headersMap, Map<String, Object> queryParamsMap,
			boolean logRequired,boolean includeAuth) {
		if (logRequired) {
			return RestAssured.given(createRequestSpec(headersMap, queryParamsMap,includeAuth)).log().all().when().log().all()
					.get(serviceUrl);
		} else {
			return RestAssured.given(createRequestSpec(headersMap, queryParamsMap,includeAuth)).when().get(serviceUrl);
		}

	}

//	POST:

	public Response post(String serviceUrl, String contentType, Object requestBody, boolean logRequired,boolean includeAuth) {
		if (logRequired) {
			return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all().when().log().all()
					.post(serviceUrl);
		} else {
			return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).when().post(serviceUrl);
		}
	}

	public Response post(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap,
			boolean logRequired,boolean includeAuth) {
		if (logRequired) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).log().all().when().log()
					.all().post(serviceUrl);
		} else {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).when().post(serviceUrl);
		}
	}

//	PUT:

	public Response put(String serviceUrl, String contentType, Object requestBody, boolean logRequired,boolean includeAuth) {
		if (logRequired) {
			return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all().when().log().all()
					.put(serviceUrl);
		} else {
			return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).when().put(serviceUrl);
		}
	}

	public Response put(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap,
			boolean logRequired,boolean includeAuth) {
		if (logRequired) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).log().all().when().log()
					.all().put(serviceUrl);
		} else {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).when().put(serviceUrl);
		}
	}

//	PATCH:

	public Response patch(String serviceUrl, String contentType, Object requestBody, boolean logRequired,boolean includeAuth) {
		if (logRequired) {
			return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all().when().log().all()
					.patch(serviceUrl);
		} else {
			return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).when().patch(serviceUrl);
		}
	}

	public Response patch(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap,
			boolean logRequired,boolean includeAuth) {
		if (logRequired) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).log().all().when().log()
					.all().patch(serviceUrl);
		} else {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).when().patch(serviceUrl);
		}
	}

//	DELETE:

	public Response delete(String serviceUrl, boolean logRequired,boolean includeAuth) {
		if (logRequired) {
			return RestAssured.given(createRequestSpec(includeAuth)).log().all().when().log().all().delete(serviceUrl);
		} else {
			return RestAssured.given(createRequestSpec(includeAuth)).when().delete(serviceUrl);
		}
	}
	
	
	public String getAccessToken(String serviceURL, String grantType, String clientId, String clientSecret) {
		
        baseURI="https://test.api.amadeus.com";
	
	       String accessToken=
			            given().log().all()
			               .contentType(ContentType.URLENC)
	                       .formParam("grant_type", grantType)
	                       .formParam("client_id", clientId)
	                       .formParam("client_secret", clientSecret).
	                    when().log().all()
	                       .post(serviceURL).
	                    then().log().all()
	                       .assertThat().statusCode(200)
	                       .and()
	                       .extract().response().path("access_token");
	       
	       System.out.println("Access Token is"+accessToken);
	       return accessToken;
	
}

}

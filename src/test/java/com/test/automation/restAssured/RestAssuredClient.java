package com.test.automation.restAssured;

import java.io.IOException;

import com.test.automation.base.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestAssuredClient extends BaseTest {

	public RestAssuredClient() throws IOException {
		super();
	}

	/**
	 * GET call
	 * 
	 * @param url
	 * @return
	 */
	public Response requestGetCall(String url) {
		Response response = RestAssured.given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON,
				prop.getProperty("key"), prop.getProperty("value")).log().all().when().get(url).then().log().all()
				.extract().response();
		return response;

	}

	/**
	 * Post Call
	 * 
	 * @param url
	 * @param entityString
	 * @return
	 */
	public Response requestPostCall(String url, String entityString) {
		Response response = RestAssured
				.given().body(entityString).headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON,
						prop.getProperty("key"), prop.getProperty("value"))
				.log().all().when().post(url).then().log().all().extract().response();
		return response;

	}
}

package com.test.automation.testCases;

import java.io.IOException;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.test.automation.base.BaseTest;
import com.test.automation.constant.FrameworkConstant;
import com.test.automation.constant.StatusCodes;
import com.test.automation.restAssured.RestAssuredClient;
import com.test.automation.utility.Utility;
import com.test.automtion.dataProvider.StaticDataProvider;

import io.restassured.response.Response;

public class TestCases extends BaseTest {

	RestAssuredClient restAssuredClient;
	HashMap<String, Object> hash_map;
	Response response;
	String orderId;
	String getEndPoint;
	String Payload;

	public TestCases() throws IOException {
		super();
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws IOException {
		restAssuredClient = new RestAssuredClient();
	}

	// Test Case to create a post request with correct data and check the get end
	// point as well with the corresponding orderID

	// @Test(dataProvider = "correct-data-provider", dataProviderClass =
	// StaticDataProvider.class)
	public void test_correctData_post(int amount, String currency, String countryCode) throws IOException {

		hash_map = new HashMap<String, Object>();
		hash_map.put("amount", amount);
		hash_map.put("currency", currency);
		hash_map.put("country_code", countryCode);

		Payload = Utility.readFile("src/main/java/templateJson/test_post_expectedData.json");

		Payload = Payload.replaceAll("amountValue", String.valueOf(hash_map.get("amount")));
		Payload = Payload.replaceAll("currencyValue", String.valueOf(hash_map.get("currency")));
		Payload = Payload.replaceAll("countryCodeValue", String.valueOf(hash_map.get("country_code")));

		response = restAssuredClient.requestPostCall(prop.getProperty("baseURI"), Payload);

		// code to check status code of response
		Assert.assertEquals(response.getStatusCode(), StatusCodes.RESPONSE_STATUS_CODE_200);

		// code to check orderID is not empty and verify message of API response
		Assert.assertEquals(response.getBody().jsonPath().getString("data.message"), FrameworkConstant.SuccessStatus);
		Assert.assertFalse(response.getBody().jsonPath().getString("data.orderId").isEmpty());

		// fetch order id to pass in get request
		orderId = response.getBody().jsonPath().getString("data.orderId");
		orderId = orderId.replace("[", " ").replace("]", " ").trim();

		// Testing of the get end point with corresponding orderId
		getEndPoint = prop.getProperty("baseURI") + "?orderId=" + orderId;
		System.out.println(getEndPoint);
		response = restAssuredClient.requestGetCall(getEndPoint);

		// Assertion status code and to check fetching the correct order or not
		Assert.assertEquals(response.getStatusCode(), StatusCodes.RESPONSE_STATUS_CODE_200);
		Assert.assertEquals(response.getBody().jsonPath().getString("orderId"), orderId);

	}

	@Test(dataProvider = "incorrect-data-provider", dataProviderClass = StaticDataProvider.class)
	public void test_incorrectData_post(int amount, String currency, String countryCode) throws IOException {

		hash_map = new HashMap<String, Object>();
		hash_map.put("amount", amount);
		hash_map.put("currency", currency);
		hash_map.put("country_code", countryCode);

		String Payload = Utility.readFile("src/main/java/templateJson/test_post_expectedData.json");

		Payload = Payload.replaceAll("amountValue", String.valueOf(hash_map.get("amount")));
		Payload = Payload.replaceAll("currencyValue", String.valueOf(hash_map.get("currency")));
		Payload = Payload.replaceAll("countryCodeValue", String.valueOf(hash_map.get("country_code")));
		;

		response = restAssuredClient.requestPostCall(prop.getProperty("baseURI"), Payload);

		// code to check status code of response
		Assert.assertEquals(response.getStatusCode(), StatusCodes.RESPONSE_STATUS_CODE_400);
		Assert.assertFalse(response.getBody().jsonPath().getString("error").isEmpty());
		System.out.println(response.getBody().jsonPath().getString("error"));

	}

}

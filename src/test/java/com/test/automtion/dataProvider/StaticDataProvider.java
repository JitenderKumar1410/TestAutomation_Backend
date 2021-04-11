package com.test.automtion.dataProvider;

import org.testng.annotations.DataProvider;

public class StaticDataProvider {

	// Pass correct data to API
	@DataProvider(name = "correct-data-provider")
	public Object[][] dpCorrectMethod() {
		return new Object[][] { { 0, "EUR", "DE" }, { 999, "EUR", "DE" }, { 1000, "EUR", "DE" }, { 0, "AUD", "AU" },
				{ 999, "AUD", "AU" }, { 1000, "AUD", "AU" }, { 0, "SGD", "SG" }, { 999, "SGD", "SG" },
				{ 1000, "SGD", "SG" } };
	}

	// Pass wrong data to API
	@DataProvider(name = "incorrect-data-provider")
	public Object[][] dpIncorrectMethod() {
		return new Object[][] { { 10000, "EUR", "DE" }, { 999, "AB", "DE" }, { 1000, "EUR", "NN" }, { 0, "AUD", "EUR" },
				{ 999, "7", "AU" }, { 1000, "MN", "AU" }, { 9999, "MN", "SG" } };
	}

}

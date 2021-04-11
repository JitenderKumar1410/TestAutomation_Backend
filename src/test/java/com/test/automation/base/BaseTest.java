package com.test.automation.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Global class for reading configuration file and host URL
 * 
 * @author jitenderKumar
 *
 */
public class BaseTest {
	protected Properties prop = null;
	protected FileInputStream input = null;

	/**
	 * 
	 * @throws IOException
	 */
	public BaseTest() throws IOException {
		try {
			input = new FileInputStream(
					System.getProperty("user.dir") + "/config.properties");
			prop = new Properties();
			prop.load(input);

			input.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
}

package com.taf.base;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taf.api.endpoints.APIEndPoints;
import com.taf.api.utilities.Constants;
import com.taf.api.utilities.JsonPath;
import com.taf.api.utilities.PropertyHolder;
import com.taf.api.utilities.Utility;

public class Base {
	public static final Logger LOGGER = LoggerFactory.getLogger(Base.class);
	public static Properties properties = new Properties();
	public static Properties envProperties = new Properties();

	static {

		readPropertyFile();
		readEnvPropertyFile();
		init();
	}

	/**
	 * Initialize config.property available under resource folder
	 * 
	 */
	public static void init() {
		try {
			Utility.putVariablesInMap(APIEndPoints.class);
			Utility.putVariablesInMap(JsonPath.class);
			Utility.putVariablesInMap(Constants.class);
			LOGGER.info("Constants loaded in Properties map");
		} catch (Exception e) {
			Assert.assertTrue("Error while reading properties file: " + e.getMessage(), false);
		}
	}

	public static void readPropertyFile() {
		try {
			FileInputStream stream = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/config/apiProperties/config.properties");
			properties.load(stream);
			if (properties.size() > 0) {
				Set<Object> keys = properties.keySet();
				for (Object object : keys) {
					PropertyHolder.setProperty(object.toString(), properties.getProperty(object.toString()));
				}
			}
		} catch (IOException e) {
			Assert.assertTrue("Error on reading config file" + e.getMessage(), false);
		}
	}

	public static void readEnvPropertyFile() {
		try {
			String environment = properties.getProperty("environment");

			FileInputStream stream = new FileInputStream(System.getProperty("user.dir")
					+ "/src/test/resources/config/apiProperties/environments/" + environment + ".properties");
			envProperties.load(stream);
			if (properties.size() > 0) {
				Set<Object> keys = envProperties.keySet();
				for (Object object : keys) {
					PropertyHolder.setProperty(object.toString(), envProperties.getProperty(object.toString()));
					LOGGER.info(object.toString() + " : " + envProperties.getProperty(object.toString()));
				}
			}
		} catch (IOException e) {
			org.junit.Assert.assertTrue("Error on reading environment config file" + e.getMessage(), false);
		}
	}

}

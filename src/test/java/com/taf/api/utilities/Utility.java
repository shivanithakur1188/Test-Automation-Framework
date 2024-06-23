package com.taf.api.utilities;

import static com.taf.api.utilities.PropertyHolder.getProperty;
import static com.taf.api.utilities.PropertyHolder.setProperty;
import static io.restassured.config.EncoderConfig.encoderConfig;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import com.taf.base.Base;

/**
 * The Class Utility.
 */
public class Utility {

	/**
	 * Read json file from requestJson.
	 *
	 * @param jsonName the json name
	 * @return the string
	 */
	public static String readJson(String jsonName) {
		String jsonString = null;
		try {
			URL file = Resources.getResource("config/json/RequestJson/" + jsonName + ".json");
			jsonString = Resources.toString(file, Charsets.UTF_8);

		} catch (Exception e) {
			Assert.assertFalse("Error while reading json file: " + jsonName + " " + e.getMessage(), true);
		}
		return jsonString;
	}

	/**
	 * Creates the end point by concating base_url and apiEndpoint.
	 *
	 * @param base_url    the base url
	 * @param apiEndpoint the api endpoint
	 * @return the string
	 */
	public static String createEndPoint(String base_url, String apiEndpoint) {
		return base_url.concat(apiEndpoint);
	}

	/**
	 * Put variables in map.
	 *
	 * @param clz the clz
	 * @throws Exception the exception
	 */
	public static void putVariablesInMap(Class clz) throws Exception {
		Field[] fields = clz.getFields();
		for (Field field : fields) {
			setProperty(field.getName(), (String) field.get(field));
		}
	}

	/**
	 * Gets the map from data table using key.
	 *
	 * @param table the table
	 * @param key   the key
	 * @return the map from data table using key
	 */
	public static Map<String, String> getMapFromDataTableUsingKey(DataTable table, String key) {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < table.asLists().size(); i++) {
			if (table.asLists().get(i).get(0).equalsIgnoreCase(key)) {
				map.put(table.asLists().get(i).get(1), PropertyHolder.getProperty(table.asLists().get(i).get(2)) == null
						? Base.properties.get(table.asLists().get(i).get(2)) == null ? table.asLists().get(i).get(2)
								: Base.properties.get(table.asLists().get(i).get(2)).toString()
						: getProperty(table.asLists().get(i).get(2)));
			}
		}
		return map;
	}

	/**
	 * Gets the content type from data table.
	 *
	 * @param table the datatable
	 * @return the content type from data table
	 */
	public static String getContentTypeFromDataTable(DataTable table) {
		String contentType = null;
		for (int i = 0; i < table.asLists().size(); i++) {
			if (table.asLists().get(i).get(0).equalsIgnoreCase("contenttype")) {
				contentType = table.asLists().get(i).get(1);
			}
		}
		return contentType;
	}

	/**
	 * Compare values.
	 *
	 * @param actualValue   the actual value in string
	 * @param expectedValue the expected value in string
	 */
	public static void compareValues(String actualValue, String expectedValue) {
		Assert.assertTrue(
				"Values are not matching, actual value: " + actualValue + ", but expected was: " + expectedValue,
				actualValue.equals(expectedValue));
	}

	/**
	 * Sets the values in map by searching for specific keywords.
	 *
	 * @param map the map
	 * @return the map
	 */
	public static Map<String, String> setValuesInMap(Map<String, String> map) {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String updatedValue = PropertyHolder.booleanProperty(entry.getValue()) ? getProperty(entry.getValue())
					: entry.getValue();
			map.put(entry.getKey(), updatedValue);
		}
		return map;
	}

	/**
	 * Updates the request body as per dataTable.
	 *
	 * @param table    the DataTable
	 * @param jsonBody the json body
	 * @return the string
	 */
	public static String updateRequestBody(DataTable table, String jsonBody) {
		for (int i = 0; i < table.asLists().size(); i++) {
			switch (table.asLists().get(i).get(0).toLowerCase()) {
			case "key to be update": {
				jsonBody = Utility.updateKeyValueInJson(jsonBody, table.asLists().get(i).get(1),
						table.asLists().get(i).get(2));
				break;
			}
			}

		}
		return jsonBody;
	}

	/**
	 * Update key value in json.
	 *
	 * @param json             the json in which key's value to be updated
	 * @param jsonPath         the jsonpath for key
	 * @param valueToBeUpdated the value to be updated
	 * @return the updated json in string
	 */
	public static String updateKeyValueInJson(String json, String jsonPath, String valueToBeUpdated) {
		DocumentContext updateJson = null;
		try {
			Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);
			valueToBeUpdated = PropertyHolder.booleanProperty(valueToBeUpdated) ? getProperty(valueToBeUpdated)
					: valueToBeUpdated;
			jsonPath = getProperty(jsonPath) == null ? jsonPath : getProperty(jsonPath);

			Object addObject = Configuration.defaultConfiguration().jsonProvider().parse(valueToBeUpdated);

			updateJson = JsonPath.parse(document).set(jsonPath, addObject);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage(), false);
		}
		return updateJson.jsonString();
	}

	/**
	 * Do request.
	 *
	 * @param requestSpec the request spec
	 * @return the response
	 */
	public static Response doRequest(RequestSpecification requestSpec) {
		ResponseUtils.request = requestSpec;
		switch (getProperty(Constants.METHOD_TYPE).toLowerCase()) {
		case "get": 
			return requestSpec.log().all().relaxedHTTPSValidation().get(getProperty(Constants.URL));
		case "post":
			return requestSpec.log().all().relaxedHTTPSValidation()
					.body(getProperty(Constants.REQUEST_JSON)).post(getProperty(Constants.URL));
		case "put":
			return requestSpec.log().all().relaxedHTTPSValidation()
					.body(getProperty(Constants.REQUEST_JSON)).put(getProperty(Constants.URL));
		case "delete":
			return requestSpec.log().all().relaxedHTTPSValidation().delete(getProperty(Constants.URL));
		case "patch":
			return requestSpec.log().all().relaxedHTTPSValidation().patch(getProperty(Constants.URL));
		default:
			Assert.assertTrue("Invalid method type passed: " + getProperty(Constants.METHOD_TYPE), false);
			return null;
		}
	}

	/**
	 * Builds the request.
	 *
	 * @param table      the table
	 * @param methodType the method type
	 * @return the response
	 */
	public static Response buildRequest(DataTable table, String methodType) {
		setProperty(Constants.METHOD_TYPE, methodType);
		return doRequest(SerenityRest.given()
				.contentType(getContentTypeFromDataTable(table) == null ? "application/json"
						: getContentTypeFromDataTable(table))
				.headers(Utility.setValuesInMap(getMapFromDataTableUsingKey(table, "header")))
				.formParams(Utility.setValuesInMap(getMapFromDataTableUsingKey(table, "formParam")))
				.pathParams(Utility.setValuesInMap(getMapFromDataTableUsingKey(table, "pathParam")))
				.queryParams(Utility.setValuesInMap(getMapFromDataTableUsingKey(table, "queryParam")))
				.urlEncodingEnabled(false).config(RestAssured.config()
						.encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))));
	}

	/**
	 * Builds the request.
	 *
	 * @param methodType the method type
	 * @return the response
	 */
	public static Response buildRequest(String methodType) {
		setProperty(Constants.METHOD_TYPE, methodType);
		return doRequest(SerenityRest.given().contentType("application/json").config(RestAssured.config()
				.encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))));
	}
}

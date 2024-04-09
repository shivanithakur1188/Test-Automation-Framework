package com.taf.api.utilities;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * The Class ResponseUtils.
 */
public class ResponseUtils {

    /** The request payload. */
    public static String requestPayload;

    /** The response. */
    public static Response response;
    
    /** The request. */
    public static RequestSpecification request;

    /**
     * Gets the data from response using json path.
     *
     * @param jsonPath
     *            the json path
     * @return the data from response using json path
     */
    public static String getDataFromResponseUsingJsonPath(String jsonPath) {
        return response.then().extract().jsonPath().getString(jsonPath);
    }

    /**
     * Assert response status.
     *
     * @param expectedStatusCode
     *            the expected status code
     */
	public static void assertResponseStatus(int expectedStatusCode) {
		response.then().statusCode(expectedStatusCode);
	}

}

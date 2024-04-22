package com.taf.api.stepDefs;

import static com.taf.api.utilities.PropertyHolder.getProperty;
import static com.taf.api.utilities.PropertyHolder.setProperty;
import static com.taf.api.utilities.ResponseUtils.response;

import com.taf.api.utilities.JsonPath;
import io.cucumber.java.en.And;
import org.junit.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.taf.base.Base;
import com.taf.api.utilities.Constants;
import com.taf.api.utilities.ResponseUtils;
import com.taf.api.utilities.Utility;


public class CommonStepDef {
	@Given("^I have the endpoint as \"([^\"]*)\"$")
	public void i_have_the_endpoint_as_something(String endpoint) throws Throwable {
		setProperty(Constants.URL,
				Utility.createEndPoint(Base.envProperties.get(Constants.API_BASE_URL).toString(), getProperty(endpoint)));
		Base.LOGGER.info("Created endpoint is:"+getProperty(Constants.URL));
	}

	@When("^I send the \"([^\"]*)\" request to \"([^\"]*)\"$")
	public void i_send_the_something_request_to_something(String methodType, String desc)
			throws Throwable {
		response = Utility.buildRequest(methodType);

	}

	@When("^I send the \"([^\"]*)\" request to \"([^\"]*)\" using request body as \"([^\"]*)\"$")
	public void i_send_the_something_request_to_something_using_request_body_as_something(String methodType,
			String desc, String jsonBody) throws Throwable {
		setProperty(Constants.REQUEST_JSON, Utility.readJson(jsonBody));
		response = Utility.buildRequest(methodType);
	}
	
	@When("^I send the \"([^\"]*)\" request to \"([^\"]*)\" using request body as \"([^\"]*)\" using$")
	public void i_send_the_something_request_to_something_using_request_body_as_something_using(String methodType,
			String desc, String jsonBody, DataTable table) throws Throwable {
		setProperty(Constants.REQUEST_JSON, Utility.updateRequestBody(table, Utility.readJson(jsonBody)));
		response = Utility.buildRequest(methodType);
		
	}

	@Then("^I should see the response status code as \"([^\"]*)\"$")
	public void i_should_see_the_response_status_code_as_something(int strArg1) throws Throwable {
		ResponseUtils.assertResponseStatus(strArg1);
		response.prettyPrint();
	}

	@Then("^I should see the following parameters in response$")
	public void i_should_see_the_following_parameters_in_response(DataTable table) throws Throwable {
		for (int i = 1; i < table.asLists().size(); i++) {
			if (table.asLists().get(i).get(1).contains("JSON_PATH_")) {
				if (ResponseUtils.getDataFromResponseUsingJsonPath(getProperty(table.asLists().get(i).get(1))) != null) {
					setProperty(table.asLists().get(i).get(1).replace("JSON_PATH_", ""),
							ResponseUtils.getDataFromResponseUsingJsonPath(getProperty(table.asLists().get(i).get(1))));

				} else
					Assert.assertTrue("key not found in response: " + getProperty(table.asLists().get(i).get(1)), false);
			} else {
				if (ResponseUtils.getDataFromResponseUsingJsonPath(table.asLists().get(i).get(1)) != null)
					ResponseUtils.getDataFromResponseUsingJsonPath(table.asLists().get(i).get(1));

				else
					Assert.assertTrue("key not found in response: " + table.asLists().get(i).get(1), false);
			}

		}

	}

	@Then("^I should see the following parameters in response as$")
	public void i_should_see_the_following_parameters_in_response_as(DataTable table) throws Throwable {
		for (int i = 1; i < table.asLists().size(); i++) {
			Utility.compareValues(
					ResponseUtils.getDataFromResponseUsingJsonPath(
							getProperty(table.asLists().get(i).get(1)) == null ? table.asLists().get(i).get(1)
									: getProperty(table.asLists().get(i).get(1))),
					getProperty(table.asLists().get(i).get(2)) == null ? table.asLists().get(i).get(2)
							: getProperty(table.asLists().get(i).get(2)));
		}
	}
	
	@Then("^I should verify printing of logs$")
	public void i_should_sollowing_parameters_in_response_as() {
		System.out.println("SYSOUt logs");
		Base.LOGGER.info("Logs printed successfully");
		
		
		}

	@Given("I have endpoint as {string}")
	public void userSetTheRequestUrlAs(String endpoint) {
//		System.out.println("@@@@@@@Request URL is following1 -->  " + endpoint.split("/")[0]);
//		System.out.println("@@@@@@Request URL is following1 -->  " + endpoint.split("/")[1]);
//		System.out.println("@@@@@@@Request URL is following2 -->  " + Base.envProperties.get(endpoint.split("/")[0]));
//		System.out.println("@@@@@@@Request URL is following2 -->  " + getProperty(endpoint.split("/")[1]));

		setProperty(Constants.URL,
				Utility.createEndPoint(Base.envProperties.get(endpoint.split("/")[0]).toString(), getProperty(endpoint.split("/")[1])));
		Base.LOGGER.info("Created endpoint is:"+getProperty(Constants.URL));
	}

	@And("Get the ID from the response")
	public void getTheFromTheResponse() {

		Base.LOGGER.info("Newly created Article ID is: "+ResponseUtils.getDataFromResponseUsingJsonPath(JsonPath.JSON_PATH_ARTICLE_ID));

	}
}
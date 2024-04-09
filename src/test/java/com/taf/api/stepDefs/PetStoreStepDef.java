package com.taf.api.stepDefs;

import org.json.JSONArray;
import org.junit.Assert;

import io.cucumber.java.en.And;

import static com.taf.api.utilities.ResponseUtils.response;

public class PetStoreStepDef {
	@And("^I should see list of available pets$")
	public void iShouldSeeListOfAvailablePets() {
		JSONArray res = new JSONArray(response.asString());
		for (int i = 0; i < res.length(); i++) {
			Assert.assertTrue("Status is not equal to available",
					res.getJSONObject(i).getString("status").equals("available"));
		}
	}
}

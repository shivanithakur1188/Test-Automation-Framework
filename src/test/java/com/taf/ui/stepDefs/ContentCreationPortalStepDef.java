package com.taf.ui.stepDefs;

import com.taf.base.Base;
import com.taf.ui.pages.HomePage;
import com.taf.ui.pages.LoginPage;
import com.taf.ui.utilities.SerenityActions;
import com.taf.utility.Assertions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import org.junit.Assert;

public class ContentCreationPortalStepDef {

    @Steps
    SerenityActions actions;

    LoginPage loginPage;
    HomePage homePage;

    @Given("The user logged in to the application")
    public void theUserLoggedInToTheApplication() {
        Base.LOGGER.info("test started");
        loginPage.open();
        actions.type("v.gomathi@globallogic.com",loginPage.userEmail);
        actions.type("Test@123",loginPage.passwordEle);
        actions.click(loginPage.submit);
    }

    @When("The user clicked on content manager")
    public void theUserClickedOnContentManager() {
        actions.waitUntilVisible(homePage.contentManager);
        actions.click(homePage.contentManager);
    }

    @Then("The user should see a Create new entry button")
    public void theUserShouldSeeACreateNewEntryButton() {
        actions.waitUntilVisible(homePage.createNewEntryButton);
        actions.click(homePage.createNewEntryButton);
    }

    @Then("The user should find all mandatory and non mandatory fields on the form")
    public void theUserShouldFindAllMandatoryAndNonMandatoryFieldsOnTheForm() {
    }
}

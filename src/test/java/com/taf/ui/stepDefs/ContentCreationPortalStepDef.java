package com.taf.ui.stepDefs;

import com.taf.base.Base;
import com.taf.ui.pages.CreateArticlePage;
import com.taf.ui.pages.HomePage;
import com.taf.ui.pages.LoginPage;
import com.taf.ui.utilities.SerenityActions;
import com.taf.utility.Assertions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

import java.util.List;

public class ContentCreationPortalStepDef {

    @Steps
    SerenityActions actions;

    LoginPage loginPage;
    HomePage homePage;
    CreateArticlePage articlePage;

    @Given("The user logged in to the application")
    public void theUserLoggedInToTheApplication() {
        Base.LOGGER.info("test started");
        loginPage.open();
        actions.waitUntilVisible(loginPage.userEmail);
        actions.type("v.gomathi@globallogic.com", loginPage.userEmail);
        actions.type("Test@123", loginPage.passwordEle);
        actions.click(loginPage.submit);
    }

    @When("The user clicked on content manager")
    public void theUserClickedOnContentManager() {
        actions.click(homePage.contentManager);
    }

    @Then("The user should see a Create new entry button")
    public void theUserShouldSeeACreateNewEntryButton() {
        actions.waitUntilVisible(homePage.createNewEntryButton);
        actions.click(homePage.createNewEntryButton);
    }

    @And("The user clicked Save button after entering Article title")
    public void theUserClickedSaveButtonAfterEnteringArticleTitle(DataTable table) throws InterruptedException {
        actions.waitUntilVisible(articlePage.articleTitleTextbox);
        actions.type(table.cell(0, 0).trim(), articlePage.articleTitleTextbox);
        actions.click(articlePage.saveEntryButton);

    }

    @Then("The user should not be able to save form with blank {string} field")
    public void theUserShouldNotBeAbleToSaveFormWithBlankField(String mandatoryField) {
        Assertions.assertTrue(actions.isCurrentlyEnable(articlePage.saveEntryButton), "Save button is not enabled");
        actions.waitUntilVisible(articlePage.errorLabelCategory);
        Assertions.assertTrue(articlePage.errorLabelCategory.isCurrentlyVisible(),
            "Error message should be displayed when Category field is blank");
    }

    @Then("The user should find the Save button disabled")
    public void theUserShouldFindTheSaveButtonDisabled() {
        Assertions.assertTrue(actions.isCurrentlyNotEnable(articlePage.saveEntryButton), "Save button is enabled");
    }

    @When("The user entered Article title")
    public void theUserEnteredArticleTitle(DataTable table) throws InterruptedException {
        actions.waitUntilVisible(articlePage.articleTitleTextbox);
        actions.type(table.cell(0, 0).trim(), articlePage.articleTitleTextbox);
    }

    @When("The user clicked back button to leave page")
    public void theUserClickedBackButtonToLeavePage() {
        actions.click(articlePage.backButton);
    }

    @Then("The user should get a confirmation popup")
    public void theUserShouldGetAConfirmationPopup(DataTable table) {
        actions.waitForAlert();
        Assertions.assertTrue(actions.getAlertText().equals(table.cell(0, 0).trim()), "Alert text not matching");
        actions.waitForAlertAndCancel();
    }

    @Then("The user should see all mandatory fields on the form")
    public void theUserShouldFindAllMandatoryFieldsOnTheForm(List<String> fields) {
        for (String field : fields) {
            Assertions.assertTrue(actions.isCurrentlyVisible(articlePage.mandatoryFieldName(field.trim())),
                "Mandatory field is not visible: " + field);
        }
    }

    @When("The user filled data in all fields and saved draft")
    public void theUserFilledDataInAllFieldsAndSavedDraft() {
        actions.type("Article Title", articlePage.articleTitleTextbox);
        actions.type("Subtitle of Newly created article through automation", articlePage.subTitleTextbox);
        actions.typeAndEnter("Global", articlePage.categoryDropDown);
        actions.type(
            "Body Content of Newly created article through automation. This is a POC of UI automation through Java and Selenium",
            articlePage.bodyContentTextArea);
        actions.click(articlePage.location);
        actions.click(articlePage.mandatoryFieldName("Top Story"));
        actions.click(articlePage.selectHeroImageButton);
        actions.click(articlePage.image);
        actions.click(articlePage.finishButton);
        actions.click(articlePage.saveEntryButton);
    }

    @Then("The user should be able to Publish the article")
    public void theUserShouldBeAbleToPublishTheArticle() {
        actions.waitUntilVisible(articlePage.publishEntryButton);
        actions.click(articlePage.publishEntryButton);
        actions.waitUntilVisible(articlePage.unpublishEntryButton);
    }
}

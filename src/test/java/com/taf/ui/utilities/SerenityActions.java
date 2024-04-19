package com.taf.ui.utilities;

import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.model.collect.NewList;

public class SerenityActions extends PageObject {

    @Step("waits for alert and accepts it")
    public void waitForAlertAndAccept(WebDriver driver) {
        waitForAlert();
        driver.switchTo().alert().accept();
    }

    @Step("waits for alert and cancel it")
    public void waitForAlertAndCancel() {
        waitForAlert();
        getDriver().switchTo().alert().dismiss();
    }

    @Step("waits for alert")
    public void waitForAlert() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public String getAlertText() {
        return getDriver().switchTo().alert().getText();
    }

    @SuppressWarnings("unchecked")
    @Step("clicks on {0}")
    public void click(WebElementFacade element) {
        waitUntilPresent(element);
        waitUntilClickable(element);

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.pollingEvery(Duration.ofSeconds(1)).ignoreAll(NewList.of(StaleElementReferenceException.class,
            ElementClickInterceptedException.class))
            .until((ExpectedCondition<Boolean>) driver -> {
                element.click();
                return true;
            });
    }

    @Step("waits for {0} to be present")
    public void waitUntilPresent(WebElementFacade element) {
        element.waitUntilPresent();
    }

    @Step("waits for {0} to be clickable")
    public void waitUntilClickable(WebElementFacade element) {
        element.waitUntilClickable();
    }

    @Step("waits for {0} to be enabled")
    public void waitUntilEnabled(WebElementFacade element) {
        element.waitUntilEnabled();
    }

    @Step("waits for {0} to be visible")
    public void waitUntilVisible(WebElementFacade element) {
        element.waitUntilVisible();
    }

    @Step("checks if {0} is currently visible")
    public boolean isCurrentlyVisible(WebElementFacade element) {
        return element.isCurrentlyVisible();
    }

    @Step("checks if {0} is currently enabled")
    public boolean isCurrentlyEnable(WebElementFacade element) {
        return element.isCurrentlyEnabled();
    }

    @Step("checks if {0} is currently not visible")
    public boolean isCurrentlyNotVisible(WebElementFacade element) {
        return !element.isCurrentlyVisible();
    }

    @Step("checks if {0} is currently not enabled")
    public boolean isCurrentlyNotEnable(WebElementFacade element) {
        return !element.isCurrentlyEnabled();
    }

    @Step("waits for {0} to be not visible")
    public void waitUntilNotVisible(WebElementFacade element) {
        element.waitUntilNotVisible();
    }

    @Step("enters '{0}' into {1}")
    public void type(String text, WebElementFacade element) {
        element.type(text);
    }

    @Step("enters '{0}' into {1} and presses Enter")
    public void typeAndEnter(String text, WebElementFacade element) {
        element.typeAndEnter(text);
    }

}

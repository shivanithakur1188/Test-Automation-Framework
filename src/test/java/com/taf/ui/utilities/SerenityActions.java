package com.taf.ui.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class SerenityActions extends PageObject{

    @Step("waits for alert and accepts it")
    public void waitForAlertAndAccept(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
    
    @Step("clicks on {0}")
    public void click(WebElementFacade element) {
    	element.waitUntilVisible().and().waitUntilClickable().click();
    }
    
    @Step("waits for {0} to be visible")
    public void waitUntilVisible(WebElementFacade element) {
    	element.waitUntilVisible();
    }
    
    @Step("waits for {0} to be not visible")
    public void waitUntilNotVisible(WebElementFacade element) {
    	element.waitUntilNotVisible();
    }
    
    @Step("enters '{0}' into {1}")
    public void type(String text, WebElementFacade element) {
    	element.type(text);
    }
    
}

package com.taf.ui.pages;



import org.openqa.selenium.WebDriver;

import com.taf.ui.utilities.SerenityActions;


import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class ProductPage extends PageObject {
    
    
	@Steps
	SerenityActions actions;

	@FindBy(xpath = "//a[text()='Add to cart']")
	public WebElementFacade addToCart;
	
	@FindBy(xpath = "//h3")
	public WebElementFacade productPrice;

    public void acceptAlert() {
        actions.waitForAlertAndAccept(getDriver());
    } 

}

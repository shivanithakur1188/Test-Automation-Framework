package com.taf.ui.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class ProductPage extends PageObject {
	@FindBy(xpath = "//a[text()='Add to cart']")
	public WebElementFacade addToCart;
	
	@FindBy(xpath = "//h3")
	public WebElementFacade productPrice; 

}

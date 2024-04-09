package com.taf.ui.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class CartPage extends PageObject {
	@FindBy(xpath = "//button[text()='Place Order']")
	public WebElementFacade placeOrder;

	@FindBy(xpath = "//h5[text()='Place order']")
	public WebElementFacade placeOrderForm;

	public WebElementFacade deleteItem(String name) {
		return findBy("//td[text()='" + name + "']/following-sibling::td/a");
	}

	@FindBy(xpath = "//a[text()='Delete']/../preceding-sibling::td[1]")
	public WebElementFacade itemPrice;

	@FindBy(id = "name")
	public WebElementFacade formNameField;

	@FindBy(id = "country")
	public WebElementFacade formCountryField;

	@FindBy(id = "city")
	public WebElementFacade formCityField;

	@FindBy(id = "card")
	public WebElementFacade formCreditCardField;

	@FindBy(id = "month")
	public WebElementFacade formMonthField;

	@FindBy(id = "year")
	public WebElementFacade formYearField;

	@FindBy(xpath = "//button[text()='Purchase']")
	public WebElementFacade purchase;

	@FindBy(xpath = "//h2[text()='Thank you for your purchase!']")
	public WebElementFacade confirmationDialog;

	@FindBy(xpath = "//h2[text()='Thank you for your purchase!']")
	public WebElementFacade orderPrice;

	@FindBy(xpath = "//p[@class='lead text-muted ']")
	public WebElementFacade orderDetails;

	@FindBy(xpath = "//button[text()='OK']")
	public WebElementFacade okButton;
}

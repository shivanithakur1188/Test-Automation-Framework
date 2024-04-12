package com.taf.ui.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

import org.junit.Assert;

import com.taf.base.Base;
import com.taf.ui.pages.CartPage;
import com.taf.ui.pages.HomePage;
import com.taf.ui.pages.ProductPage;
import com.taf.ui.utilities.SerenityActions;

public class StoreStepDef{
	HomePage homepage;
	CartPage cartpage;
	ProductPage productPage;
	
	@Steps
	SerenityActions actions;

	public static int expectedPrices;

	@Given("the {string} is on homepage")
	public void the_is_on_homepage(String string) {
		Base.LOGGER.info("test started");
		homepage.open();
	}

	@When("user navigates to category {string}")
	public void user_navigates_to_category(String string) {
		if (string.split(",").length > 1) {
			for (int i = 0; i < string.split(",").length; i++) {
				actions.click(homepage.target(string.split(",")[i].trim()));
			}
		} else {
			actions.click(homepage.target(string.trim()));
		}

	}

	@When("user adds {string} to cart")
	public void user_adds_to_cart(String string) {
		actions.click(homepage.target(string));
		actions.click(productPage.addToCart);
		productPage.acceptAlert();
		homepage.open();
	}

	@When("user navigates to cart page")
	public void user_navigates_to() {
		actions.click(homepage.target("Cart"));
	}

	@And("^user deletes the \"([^\"]*)\" item from cart$")
	public void userDeletesTheSomethingItemFromCart(String item) {
		actions.click(cartpage.deleteItem(item));
		actions.waitUntilNotVisible(cartpage.deleteItem(item));
		actions.waitUntilVisible(cartpage.itemPrice.get(0));

		int items = cartpage.itemPrice.size();
		for (int i = 0; i < items; i++) {
			expectedPrices += Integer
					.parseInt(cartpage.itemPrice.get(i).getText());
		}
	}

	@When("user clicks on place order in cart page")
	public void user_clicks_on() {
		actions.click(cartpage.placeOrder);
	}

	@When("user fills the web form")
	public void user_fills_the_web_form() {
		actions.waitUntilVisible(cartpage.placeOrderForm);
		
		actions.type("Globallogic",cartpage.formNameField);
		actions.type("India",cartpage.formCountryField);
		actions.type("Noida",cartpage.formCityField);
		actions.type("1234567890",cartpage.formCreditCardField);
		actions.type("April",cartpage.formMonthField);
		actions.type("2024",cartpage.formYearField);
	}

	@When("user clicks on purchase button")
	public void user_clicks_on_purchase_button() {
		actions.click(cartpage.purchase);
	}

	@And("^user should see purchase confirmation$")
	public void userShouldSeePurchaseConfirmation() {
		actions.waitUntilVisible(cartpage.confirmationDialog);
	}

	@And("^user validates the final price$")
	public void userValidatesTheFinalPrice() {
		String orderDetails = cartpage.orderDetails.getText();
		String orderPrice = orderDetails.substring(orderDetails.indexOf("Amount"), orderDetails.indexOf("USD"))
				.replaceAll("[^\\d]", "");
		Assert.assertTrue("Price not matching", expectedPrices == Integer.parseInt(orderPrice));
		actions.click(cartpage.okButton);
	}

}

package com.taf.ui.stepDefs;

import io.cucumber.java.en.Given;
import com.taf.ui.pages.HomePage;

public class StoreStepDef{
	HomePage homepage;

	public static int expectedPrices;

	@Given("the {string} is on homepage")
	public void the_is_on_homepage(String string) {
		homepage.open();
	}

//	@When("user navigates to category {string}")
//	public void user_navigates_to_category(String string) {
//		if (string.split(",").length > 1) {
//			for (int i = 0; i < string.split(",").length; i++) {
//				theActorInTheSpotlight().attemptsTo(new NavigateToCategory(string.split(",")[i].trim()));
//			}
//		} else {
//			theActorInTheSpotlight().attemptsTo(new NavigateToCategory(string.trim()));
//		}
//
//	}
//
//	@When("user adds {string} to cart")
//	public void user_adds_to_cart(String string) {
//		theActorInTheSpotlight().attemptsTo(new AddToCart(string));
//		homepage.open();
//	}
//
//	@When("user navigates to cart page")
//	public void user_navigates_to() {
//		theActorInTheSpotlight().attemptsTo(new NavigateToCategory("Cart"));
//	}
//
//	@And("^user deletes the \"([^\"]*)\" item from cart$")
//	public void userDeletesTheSomethingItemFromCart(String item) {
//		theActorInTheSpotlight().attemptsTo(WaitUntil.the(CartPage.deleteItem(item), isVisible()),
//				Click.on(CartPage.deleteItem(item)), WaitUntil.the(CartPage.deleteItem(item), isNotVisible()),
//				WaitUntil.the(CartPage.itemPrice, isVisible()));
//		int items = CartPage.itemPrice.resolveAllFor(theActorInTheSpotlight()).size();
//		for (int i = 0; i < items; i++) {
//			expectedPrices += Integer
//					.parseInt(CartPage.itemPrice.resolveAllFor(theActorInTheSpotlight()).get(i).getText());
//		}
//	}
//
//	@When("user clicks on place order in cart page")
//	public void user_clicks_on() {
//		theActorInTheSpotlight().attemptsTo(WaitUntil.the(CartPage.placeOrder, isVisible()),
//				Click.on(CartPage.placeOrder));
//	}
//
//	@When("user fills the web form")
//	public void user_fills_the_web_form() {
//		theActorInTheSpotlight().attemptsTo(new PlaceOrderFormFill());
//	}
//
//	@When("user clicks on purchase button")
//	public void user_clicks_on_purchase_button() {
//		theActorInTheSpotlight().attemptsTo(Click.on(CartPage.purchase));
//	}
//
//	@And("^user should see purchase confirmation$")
//	public void userShouldSeePurchaseConfirmation() {
//		theActorInTheSpotlight().attemptsTo(WaitUntil.the(CartPage.confirmationDialog, isVisible()));
//	}
//
//	@And("^user validates the final price$")
//	public void userValidatesTheFinalPrice() {
//		String orderDetails = CartPage.orderDetails.resolveFor(theActorInTheSpotlight()).getText();
//		String orderPrice = orderDetails.substring(orderDetails.indexOf("Amount"), orderDetails.indexOf("USD"))
//				.replaceAll("[^\\d]", "");
//		Assert.assertTrue("Price not matching", expectedPrices == Integer.parseInt(orderPrice));
//		theActorInTheSpotlight().attemptsTo(Click.on(CartPage.okButton));
//	}

}

package com.taf.ui.pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

@DefaultUrl("https://www.demoblaze.com/")
public class HomePage extends PageObject {

	public WebElementFacade target(String arg) {
		return findBy("//a[text()='" + arg + "']");
	}
}

package com.taf.ui.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class HomePage extends PageObject {

    public WebElementFacade target(String arg) {
        return findBy("//a[text()='" + arg + "']");
    }

    @FindBy(xpath = "//span[contains(text(),'Content Manager')]")
    public WebElementFacade contentManager;

    @FindBy(xpath = "//a[.='Create new entry']")
    public WebElementFacade createNewEntryButton;

}

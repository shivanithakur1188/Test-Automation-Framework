package com.taf.ui.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class LoginPage extends PageObject {

    @FindBy(xpath = "//button[contains(@class, 'sc-gsDKAQ')]")
    public WebElementFacade userName;

    @FindBy(xpath = "//*[@id=':r4:']")
    public WebElementFacade userEmail ;

    @FindBy(xpath = "//*[@id=':r6:']")
    public WebElementFacade passwordEle;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElementFacade submit;

}

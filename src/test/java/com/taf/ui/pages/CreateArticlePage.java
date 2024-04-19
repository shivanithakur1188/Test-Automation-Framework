package com.taf.ui.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class CreateArticlePage extends PageObject {

    @FindBy(xpath = "//input[@id='ArticleTitle']")
    public WebElementFacade articleTitleTextbox;

    @FindBy(xpath = "//input[@id='Subtitle']")
    public WebElementFacade subTitleTextbox;

    @FindBy(xpath = "//input[contains(@id, 'react-select-')]")
    public WebElementFacade categoryDropDown;

    @FindBy(xpath = "//span[@role='presentation']")
    public WebElementFacade bodyContentTextArea;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElementFacade saveEntryButton;

    @FindBy(xpath = "//p[contains(@class,'jjVrkA')]")
    public WebElementFacade errorLabelCategory;

    @FindBy(xpath = "//a[@class='sc-fvxzrP oyRxl active']")
    public WebElementFacade backButton;

    @FindBy(xpath = "//span[text()='Publish']/..")
    public WebElementFacade publishEntryButton;
    
    @FindBy(xpath = "//span[text()='Unpublish']/..")
    public WebElementFacade unpublishEntryButton;

    @FindBy(xpath = "//label[text()='Hero Image']/..//button")
    public WebElementFacade selectHeroImageButton;

    @FindBy(xpath = "//img[@alt]/../preceding-sibling::div//input")
    public WebElementFacade image;

    @FindBy(xpath = "//input[@aria-labelledby=':ree:-title']")
    public WebElementFacade imageCheckbox;

    @FindBy(xpath = "//div[@class='sc-bdvvtL sc-eFegNN bOQZK dvfRGL']")
    public WebElementFacade imageCollection;

    public WebElementFacade mandatoryFieldName(String field) {
        return find("//*[text()='" + field + "']");
    }
    
    @FindBy(xpath = "//*[text()='Choose here']")
    public WebElementFacade location;
    
    @FindBy(xpath ="//span[text()='Finish']/..")
    public WebElementFacade finishButton;
}

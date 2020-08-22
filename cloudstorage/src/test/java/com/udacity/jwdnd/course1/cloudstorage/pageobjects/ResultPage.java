package com.udacity.jwdnd.course1.cloudstorage.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultPage {

    @FindBy(className = "alert-success")
    private WebElement successMessage;

    @FindBy(className = "alert-danger")
    private WebElement errorMessage;

    @FindBy(name = "home-link")
    private WebElement goToHomeLink;

    private WebDriverWait wait;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, 10);
    }

    public void waitForLoaded() {
        wait.until(driver -> ExpectedConditions.elementToBeClickable(goToHomeLink));
    }

    public WebElement getSuccessMessage() {
        return successMessage;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public WebElement getGoToHomeLink() {
        return goToHomeLink;
    }
}

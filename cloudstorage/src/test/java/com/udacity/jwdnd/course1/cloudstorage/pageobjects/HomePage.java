package com.udacity.jwdnd.course1.cloudstorage.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    @FindBy(id = "nav-files-tab")
    private WebElement filesTab;

    private WebDriver driver;

    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 1);
    }

    public void goToHomePage(int port) {
        driver.get("http://localhost:" + port + "/home");
    }

    public void waitForLoaded() {
        wait.until(driver -> filesTab.isDisplayed());
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }
}

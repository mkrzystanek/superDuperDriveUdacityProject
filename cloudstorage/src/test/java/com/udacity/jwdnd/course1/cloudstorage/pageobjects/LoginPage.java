package com.udacity.jwdnd.course1.cloudstorage.pageobjects;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    @FindBy(xpath = "//h1[contains(text(), 'Login')]")
    private WebElement loginHeader;

    @FindBy(id = "error-message")
    private WebElement errorMessage;

    @FindBy(id="logout-message")
    private WebElement logoutMessage;

    @FindBy(id = "username-label")
    private WebElement usernameLabel;

    @FindBy(id = "inputUsername")
    private WebElement usernameInput;

    @FindBy(id = "password-label")
    private WebElement passwordLabel;

    @FindBy(id = "inputPassword")
    private WebElement passwordInput;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "signup-link")
    private WebElement signUpLink;

    private WebDriverWait wait;

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 1);
    }

    public void goToLoginPage(int port) {
        this.driver.get("http://localhost:" + port + "/login");
    }

    public void logIn(User user) {
        wait.until(driver -> loginHeader.isDisplayed());

        usernameInput.sendKeys(user.getUsername());
        passwordInput.sendKeys(user.getPassword());

        submitButton.click();
    }

    public WebElement getLoginHeader() {
        return loginHeader;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public WebElement getLogoutMessage() {
        return logoutMessage;
    }

    public WebElement getUsernameLabel() {
        return usernameLabel;
    }

    public WebElement getUsernameInput() {
        return usernameInput;
    }

    public WebElement getPasswordLabel() {
        return passwordLabel;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public WebElement getSignUpLink() {
        return signUpLink;
    }
}

package com.udacity.jwdnd.course1.cloudstorage.pageobjects;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {

    @FindBy(id = "back-to-login-link")
    private WebElement backToLoginLink;

    @FindBy(id = "signup-header")
    private WebElement signUpHeader;

    @FindBy(id = "success-message")
    private WebElement successMessage;

    @FindBy(id = "error-message")
    private WebElement errorMessage;

    @FindBy(id = "first-name-label")
    private WebElement firstNameLabel;

    @FindBy(id = "inputFirstName")
    private WebElement firstNameInput;

    @FindBy(id = "last-name-label")
    private WebElement lastNameLabel;

    @FindBy(id = "inputLastName")
    private WebElement lastNameInput;

    @FindBy(id = "username-label")
    private WebElement usernameLabel;

    @FindBy(id = "inputUsername")
    private WebElement usenameInput;

    @FindBy(id = "password-label")
    private WebElement passwordLabel;

    @FindBy(id = "inputPassword")
    private WebElement passwordInput;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    private WebDriverWait wait;

    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 1);
    }

    public void goToSignUpPage(int port) {
        driver.get("http://localhost:" + port + "/signup");
    }

    public void signUp(User user) {
        wait.until(driver -> signUpHeader.isDisplayed());

        firstNameInput.sendKeys(user.getFirstname());
        lastNameInput.sendKeys(user.getLastname());
        usenameInput.sendKeys(user.getUsername());
        passwordInput.sendKeys(user.getPassword());

        submitButton.click();
    }

    public void gotToLogin() {
        wait.until(driver -> backToLoginLink.isDisplayed());
        backToLoginLink.click();
    }

    public WebElement getBackToLoginLink() {
        return backToLoginLink;
    }

    public WebElement getSignUpHeader() {
        return signUpHeader;
    }

    public WebElement getSuccessMessage() {
        return successMessage;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public WebElement getFirstNameLabel() {
        return firstNameLabel;
    }

    public WebElement getFirstNameInput() {
        return firstNameInput;
    }

    public WebElement getLastNameLabel() {
        return lastNameLabel;
    }

    public WebElement getLastNameInput() {
        return lastNameInput;
    }

    public WebElement getUsernameLabel() {
        return usernameLabel;
    }

    public WebElement getUsenameInput() {
        return usenameInput;
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
}

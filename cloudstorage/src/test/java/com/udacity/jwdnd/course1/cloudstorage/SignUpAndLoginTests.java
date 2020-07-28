package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.UserBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignUpAndLoginTests extends CloudStorageApplicationTests {

    @Test
    public void loginPageLayout() {
        loginPage.goToLoginPage(this.port);
        assertEquals("Login", driver.getTitle(), "Failed to get to login page!");

        assertTrue(loginPage.getLoginHeader().isDisplayed(), "Login header was not displayed!");
        assertTrue(loginPage.getUsernameLabel().isDisplayed(), "Username label was not displayed!");
        assertTrue(loginPage.getUsernameInput().isDisplayed(), "Username input was not displayed!");
        assertTrue(loginPage.getPasswordLabel().isDisplayed(), "Password label was not displayed!");
        assertTrue(loginPage.getPasswordInput().isDisplayed(), "Password input was not displayed!");
        assertTrue(loginPage.getSubmitButton().isDisplayed(), "Submit button was not displayed!");
        assertTrue(loginPage.getSignUpLink().isDisplayed(), "'Go to Sign Up' link was not displayed!");
    }

    @Test
    void signUpPageLayout() {
        signUpPage.goToSignUpPage(this.port);
        assertEquals("Sign Up", driver.getTitle(), "Failed to get to sign up page!");

        assertTrue(signUpPage.getSignUpHeader().isDisplayed(), "Sign Up header was not displayed!");
        assertTrue(signUpPage.getFirstNameLabel().isDisplayed(), "First name label was not displayed!");
        assertTrue(signUpPage.getFirstNameInput().isDisplayed(), "First name input was not displayed!");
        assertTrue(signUpPage.getLastNameLabel().isDisplayed(), "Last name label was not displayed!");
        assertTrue(signUpPage.getLastNameInput().isDisplayed(), "Last name input was not displayed!");
        assertTrue(signUpPage.getUsernameLabel().isDisplayed(), "User name label was not displayed!");
        assertTrue(signUpPage.getUsenameInput().isDisplayed(), "User name input was not displayed!");
        assertTrue(signUpPage.getPasswordLabel().isDisplayed(), "User name label was not displayed!");
        assertTrue(signUpPage.getPasswordInput().isDisplayed(), "User name input was not displayed!");
        assertTrue(signUpPage.getSubmitButton().isDisplayed(), "Submit button was not displayed!");
    }

    @Test
    public void signUpLoginAndLogout() {
        User user = new UserBuilder()
                .withFirstName("Anthony")
                .withLastName("Soprano")
                .withUserName("Tony")
                .withPassword("I<3Ducks")
                .build();

        signUpPage.goToSignUpPage(this.port);
        signUpPage.signUp(user);

        assertTrue(signUpPage.getSuccessMessage().isDisplayed(),
                "Success message was not present on sign up page!");

        signUpPage.gotToLogin();
        loginPage.logIn(user);
        homePage.waitForLoaded();

        assertEquals("Home", driver.getTitle(), "User was not navigated to home page after login!");
        homePage.getLogoutButton().submit();
        loginPage.waitForLoaded();

        assertEquals("Login", driver.getTitle(), "User was not navigated to login page after logout!");
        homePage.goToHomePage(this.port);

        assertEquals("Login", driver.getTitle(),
                "User was not navigated to login after trying to access home page unauthorized!");
    }

    @Test
    public void unauthorizedLogin() {
        User user = new UserBuilder()
                .withUserName("Unregistered")
                .withPassword("123")
                .build();

        loginPage.goToLoginPage(this.port);
        loginPage.logIn(user);

        assertTrue(loginPage.getErrorMessage().isDisplayed(), "Error message was not displayed!");
    }

    @Test
    public void unauthorizedAccess() {
        assertTrue(false, "Missing test implementation.");
    }
}

package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.UserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CredentialsStorageTests extends CloudStorageApplicationTests {

    @BeforeEach
    @Override
    public void beforeEach() {
        super.beforeEach();

        User user = new UserBuilder()
                .withFirstName("Foster")
                .withLastName("Kane")
                .withUserName("Citizen")
                .withPassword("Rosebud")
                .build();

        signUpPage.goToSignUpPage(port);
        signUpPage.signUp(user);

        loginPage.goToLoginPage(port);
        loginPage.logIn(user);
    }

    @Test
    public void credentialsTabLayout() {
        goToCredentialsPanel();

        assertTrue(homePage.getAddNewCredentialButton().isDisplayed(), "'Add a New Credential' button was not displayed!");
        assertTrue(homePage.getAddNewCredentialButton().getText().contains("Add a New Credential"),
                "Incorrect text was displayed for 'Add a New Credential' button!");
    }

    private void goToCredentialsPanel() {
        homePage.goToHomePage(port);
        homePage.waitForLoaded();

        homePage.getCredentialsTab().click();
        homePage.waitForCredentialTabLoaded();
    }
}

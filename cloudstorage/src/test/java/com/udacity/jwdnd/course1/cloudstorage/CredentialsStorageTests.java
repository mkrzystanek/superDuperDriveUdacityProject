package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.UserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

        goToCredentialsPanel();
    }

    @Test
    public void credentialsTabLayoutTest() {
        assertTrue(homePage.getAddNewCredentialButton().isDisplayed(), "'Add a New Credential' button was not displayed!");
        assertTrue(homePage.getAddNewCredentialButton().getText().contains("Add a New Credential"),
                "Incorrect text was displayed for 'Add a New Credential' button!");
    }

    @Test
    public void addNewCredentialTest() {
        homePage.getAddNewCredentialButton().click();
        homePage.waitForCredentialModalLoaded();

        assertTrue(homePage.getCredentialModal().isDisplayed(), "'Add a New Credential' modal was not displayed!");
        assertTrue(homePage.getCredentialModalLabel().isDisplayed(), "'Add a New Credential' pane was not displayed!");
        assertTrue(homePage.getCredentialModalLabel().getText().contains("Credential"), "'Incorrect note modal title was displayed!");
        assertTrue(homePage.getNewCredentialUrlLabel().isDisplayed(), "URL label was not displayed!");
        assertTrue(homePage.getNewCredentialUrlLabel().getText().contains("URL"), "'Incorrect URL label text was displayed!");
        assertTrue(homePage.getNewCredentialUsernameLabel().isDisplayed(), "Credential username label was not displayed!");
        assertTrue(homePage.getNewCredentialUsernameLabel().getText().contains("Username"), "'Incorrect username label text was displayed!");
        assertTrue(homePage.getNewCredentialPasswordLabel().isDisplayed(), "Credential password label was not displayed!");
        assertTrue(homePage.getNewCredentialPasswordLabel().getText().contains("Password"), "'Incorrect password label text was displayed!");
        assertTrue(homePage.getNewCredentialUrlInput().isDisplayed(), "Credential URL input was not displayed!");
        assertTrue(homePage.getNewCredentialUsernameInput().isDisplayed(), "Credential username input was not displayed!");
        assertTrue(homePage.getNewCredentialPasswordInput().isDisplayed(), "Credential password input was not displayed!");
        assertTrue(homePage.getCredentialModalCloseButton().isDisplayed(), "Close credential modal button was not displayed!");
        assertTrue(homePage.getCredentialModalSaveButton().isDisplayed(), "Save credential button was not displayed!");

        homePage.getNewCredentialUrlInput().sendKeys("www.test.com");
        homePage.getNewCredentialUsernameInput().sendKeys("Bobby");
        homePage.getNewCredentialPasswordInput().sendKeys("pazzw0rd");
        homePage.getCredentialModalSaveButton().click();

        goToCredentialsPanel();

        assertTrue(homePage.getUploadedCredentialUrl().isDisplayed(), "Uploaded credential url was not displayed!");
        assertTrue(homePage.getUploadedCredentialUsername().isDisplayed(), "Uploaded credential username was not displayed!");
        assertTrue(homePage.getUploadedCredentialPassword().isDisplayed(), "Uploaded credential password was not displayed!");
        assertTrue(homePage.getEditCredentialButton().isDisplayed(), "Edit credential button was not displayed!");
        assertTrue(homePage.getDeleteCredentialButton().isDisplayed(), "Delete credential button was not displayed!");
    }

    @Test
    public void deleteCredentialTest() {
        homePage.getAddNewCredentialButton().click();
        homePage.waitForCredentialModalLoaded();
        homePage.getNewCredentialUrlInput().sendKeys("www.test.com");
        homePage.getNewCredentialUsernameInput().sendKeys("Bobby");
        homePage.getNewCredentialPasswordInput().sendKeys("pazzw0rd");
        homePage.getCredentialModalSaveButton().click();
        goToCredentialsPanel();

        homePage.getDeleteCredentialButton().submit();
        goToCredentialsPanel();

        assertEquals(0, homePage.getCredentials().size(), "Deleted credential was displayed!");
    }

    @Test
    public void editCredentialTest() {
        String url = "www.test.com";
        String username = "Bobby";
        String password = "pazzw0rd";

        homePage.getAddNewCredentialButton().click();
        homePage.waitForCredentialModalLoaded();
        homePage.getNewCredentialUrlInput().sendKeys(url);
        homePage.getNewCredentialUsernameInput().sendKeys(username);
        homePage.getNewCredentialPasswordInput().sendKeys(password);
        homePage.getCredentialModalSaveButton().click();
        goToCredentialsPanel();

        homePage.getEditCredentialButton().click();
        homePage.waitForCredentialModalLoaded();

        assertTrue(homePage.getNewCredentialUrlInput().isDisplayed(), "Edited credential url was not displayed!");
        assertTrue(homePage.getNewCredentialUrlInput().getAttribute("value").contains(url), "Incorrect edited credential url was displayed!");
        assertTrue(homePage.getNewCredentialUsernameInput().isDisplayed(), "Edited credential username was not displayed!");
        assertTrue(homePage.getNewCredentialUsernameInput().getAttribute("value").contains(username), "Incorrect edited credential username was displayed!");
        assertTrue(homePage.getNewCredentialPasswordInput().isDisplayed(), "Edited credential password was not displayed!");
        assertTrue(homePage.getNewCredentialPasswordInput().getAttribute("value").contains(password), "Incorrect edited credential password was displayed!");

        String newUrl = "www.news.org";
        String newUsername = "Bob";
        String newPassword = "Bobby1";

        homePage.getNewCredentialUrlInput().sendKeys(newUrl);
        homePage.getNewCredentialUsernameInput().sendKeys(newUsername);
        homePage.getNewCredentialPasswordInput().sendKeys(newPassword);
        homePage.getCredentialModalSaveButton().click();
        goToCredentialsPanel();

        assertTrue(homePage.getUploadedCredentialUrl().isDisplayed(), "Updated credential url was not displayed!");
        assertTrue(homePage.getUploadedCredentialUsername().isDisplayed(), "Uploaded credential username was not displayed!");
        assertTrue(homePage.getUploadedCredentialPassword().isDisplayed(), "Uploaded credential password was not displayed!");
        assertTrue(homePage.getUploadedCredentialUrl().getText().contains(newUrl), "Updated credential url was incorrect!");
        assertTrue(homePage.getUploadedCredentialUsername().getText().contains(newUsername), "Updated credential username was incorrect!");
        assertFalse(homePage.getUploadedCredentialPassword().getText().contains(newPassword), "Updated credential password was incorrect!");
    }

    private void goToCredentialsPanel() {
        homePage.goToHomePage(port);
        homePage.waitForLoaded();

        homePage.getCredentialsTab().click();
        homePage.waitForCredentialTabLoaded();
    }
}

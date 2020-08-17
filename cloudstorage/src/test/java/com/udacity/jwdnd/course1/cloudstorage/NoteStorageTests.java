package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.UserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NoteStorageTests extends CloudStorageApplicationTests {

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
    public void noteTabLayoutTest() {
        goToNotePanel();

        assertTrue(homePage.getAddNoteButton().isDisplayed(), "'Add a New Note' button was not displayed!");
        assertTrue(homePage.getAddNoteButton().getText().contains("Add a New Note"),
                "Incorrect text was displayed for 'Add a New Note' button!");
    }

    @Test
    public void addNoteTest() {
        goToNotePanel();

        homePage.getAddNoteButton().click();
        homePage.waitForNoteModalLoaded();

        assertTrue(homePage.getNoteModal().isDisplayed(), "'Add a New Note' pane was not displayed!");
        assertTrue(homePage.getNoteModalLabel().isDisplayed(), "'Add a New Note' pane was not displayed!");
        assertTrue(homePage.getNoteModalLabel().getText().contains("Note"), "'Incorrect note modal title was displayed!");
        assertTrue(homePage.getNewNoteTitleLabel().isDisplayed(), "Title label was not displayed!");
        assertTrue(homePage.getNewNoteTitleLabel().getText().contains("Title"), "'Incorrect title label text was displayed!");
        assertTrue(homePage.getNewNoteDescriptionLabel().isDisplayed(), "Note description label was not displayed!");
        assertTrue(homePage.getNewNoteDescriptionLabel().getText().contains("Description"), "'Incorrect description label text was displayed!");
        assertTrue(homePage.getNewNoteTitleInput().isDisplayed(), "Note title input was not displayed!");
        assertTrue(homePage.getNewNoteDescriptionInput().isDisplayed(), "Note description input was not displayed!");
        assertTrue(homePage.getCloseNoteModalButton().isDisplayed(), "Close note modal button input was not displayed!");
        assertTrue(homePage.getSaveNewNoteButton().isDisplayed(), "Save note button input was not displayed!");

        homePage.getNewNoteTitleInput().sendKeys("My Note");
        homePage.getNewNoteDescriptionInput().sendKeys("Example text");
        homePage.getSaveNewNoteButton().click();

        goToNotePanel();

        assertTrue(homePage.getUploadedNoteTitle().isDisplayed(), "Uploaded note title was not displayed!");
        assertTrue(homePage.getUploadedNoteDescription().isDisplayed(), "Uploaded note description was not displayed!");
        assertTrue(homePage.getEditNoteButton().isDisplayed(), "Edit note button was not displayed!");
        assertTrue(homePage.getDeleteNoteButton().isDisplayed(), "Delete note button was not displayed!");
    }

    @Test
    public void deleteNoteTest() {
        goToNotePanel();
        homePage.getAddNoteButton().click();
        homePage.waitForNoteModalLoaded();
        homePage.getNewNoteTitleInput().sendKeys("My Note");
        homePage.getNewNoteDescriptionInput().sendKeys("Example text");
        homePage.getSaveNewNoteButton().click();
        goToNotePanel();

        homePage.getDeleteNoteButton().submit();
        goToNotePanel();

        assertEquals(0, homePage.getNotes().size(), "Deleted note was displayed!");
    }

    @Test
    public void editNoteTest() throws InterruptedException {
        String title = "My Note";
        String description = "Example text";

        goToNotePanel();
        homePage.getAddNoteButton().click();
        homePage.waitForNoteModalLoaded();
        homePage.getNewNoteTitleInput().sendKeys(title);
        homePage.getNewNoteDescriptionInput().sendKeys(description);
        homePage.getSaveNewNoteButton().click();
        goToNotePanel();

        homePage.getEditNoteButton().click();
        homePage.waitForNoteModalLoaded();

        assertTrue(homePage.getNewNoteTitleInput().isDisplayed(), "Edited note title was not displayed!");
        assertTrue(homePage.getNewNoteTitleInput().getAttribute("value").contains(title), "Incorrect edited note title was displayed!");
        assertTrue(homePage.getNewNoteDescriptionInput().isDisplayed(), "Edited note description was not displayed!");
        assertTrue(homePage.getNewNoteDescriptionInput().getAttribute("value").contains(description), "Incorrect edited note description was displayed!");

        String newTitle = "Updated Note";
        String newDescription = "Updated Description";

        homePage.getNewNoteTitleInput().sendKeys(newTitle);
        homePage.getNewNoteDescriptionInput().sendKeys(newDescription);
        homePage.getSaveNewNoteButton().click();
        goToNotePanel();

        assertTrue(homePage.getUploadedNoteTitle().isDisplayed(), "Updated note title was not displayed!");
        assertTrue(homePage.getUploadedNoteDescription().isDisplayed(), "Uploaded note description was not displayed!");
        assertTrue(homePage.getUploadedNoteTitle().getText().contains(newTitle), "Updated note title was incorrect!");
        assertTrue(homePage.getUploadedNoteDescription().getText().contains(newDescription), "Updated note description was incorrect!");
    }

    private void goToNotePanel() {
        homePage.goToHomePage(port);
        homePage.waitForLoaded();

        homePage.getNotesTab().click();
        homePage.waitForNoteTabLoaded();
    }
}

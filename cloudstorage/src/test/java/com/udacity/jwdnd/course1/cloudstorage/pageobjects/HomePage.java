package com.udacity.jwdnd.course1.cloudstorage.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {

    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    @FindBy(id = "nav-files-tab")
    private WebElement filesTab;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "file-upload-label")
    private WebElement fileUploadLabel;

    @FindBy(id = "fileUpload")
    private WebElement fileUploadInput;

    @FindBy(id = "file-submit-button")
    private WebElement fileUploadButton;

    @FindBy(id = "view-file-button")
    private WebElement viewFileButton;

    @FindBy(id = "delete-file-button")
    private WebElement deleteFileButton;

    @FindBy(id = "file-list")
    private List<WebElement> files;

    @FindBy(id = "nav-notes")
    private WebElement notesTabPane;

    @FindBy(id = "add-new-note")
    private WebElement addNoteButton;

    @FindBy(id = "noteModal")
    private WebElement noteModal;

    @FindBy(id = "noteModalLabel")
    private WebElement noteModalLabel;

    @FindBy(id = "note-title-label")
    private WebElement newNoteTitleLabel;

    @FindBy(id = "note-title")
    private WebElement newNoteTitleInput;

    @FindBy(id = "note-description-label")
    private WebElement newNoteDescriptionLabel;

    @FindBy(id = "note-description")
    private WebElement newNoteDescriptionInput;

    @FindBy(id = "close-button")
    private WebElement closeNoteModalButton;

    @FindBy(id = "save-button")
    private WebElement saveNewNoteButton;

    @FindBy(id = "uploaded-note-title")
    private WebElement uploadedNoteTitle;

    @FindBy(id = "uploaded-note-desc")
    private WebElement uploadedNoteDescription;

    @FindBy(id = "edit-note")
    private WebElement editNoteButton;

    @FindBy(id = "delete-note")
    private WebElement deleteNoteButton;

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

    public void waitForNoteTabLoaded() {
        wait.until(driver -> notesTabPane.isDisplayed());
    }

    public void waitForNoteModalLoaded() {
        wait.until(driver -> noteModal.isDisplayed());
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public WebElement getFilesTab() {
        return filesTab;
    }

    public WebElement getNotesTab() {
        return notesTab;
    }

    public WebElement getCredentialsTab() {
        return credentialsTab;
    }

    public WebElement getFileUploadLabel() {
        return fileUploadLabel;
    }

    public WebElement getFileUploadInput() {
        return fileUploadInput;
    }

    public WebElement getFileUploadButton() {
        return fileUploadButton;
    }

    public WebElement getViewFileButton() {
        return viewFileButton;
    }

    public WebElement getDeleteFileButton() {
        return deleteFileButton;
    }

    public List<WebElement> getFiles() {
        return files;
    }

    public WebElement getAddNoteButton() {
        return addNoteButton;
    }

    public WebElement getNotesTabPane() {
        return notesTabPane;
    }

    public WebElement getNoteModal() {
        return noteModal;
    }

    public WebElement getNoteModalLabel() {
        return noteModalLabel;
    }

    public WebElement getNewNoteTitleLabel() {
        return newNoteTitleLabel;
    }

    public WebElement getNewNoteTitleInput() {
        return newNoteTitleInput;
    }

    public WebElement getNewNoteDescriptionLabel() {
        return newNoteDescriptionLabel;
    }

    public WebElement getNewNoteDescriptionInput() {
        return newNoteDescriptionInput;
    }

    public WebElement getCloseNoteModalButton() {
        return closeNoteModalButton;
    }

    public WebElement getSaveNewNoteButton() {
        return saveNewNoteButton;
    }

    public WebElement getUploadedNoteTitle() {
        return uploadedNoteTitle;
    }

    public WebElement getUploadedNoteDescription() {
        return uploadedNoteDescription;
    }

    public WebElement getEditNoteButton() {
        return editNoteButton;
    }

    public WebElement getDeleteNoteButton() {
        return deleteNoteButton;
    }
}

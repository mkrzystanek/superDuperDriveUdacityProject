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

    public WebElement getFilesTab() {
        return filesTab;
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
}

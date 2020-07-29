package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.exceptions.FileException;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.UserBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilesStoringTests extends CloudStorageApplicationTests {

    private final Path pathToTestFiles =  Paths.get("testFiles");
    private final Path fileUploadFolder =  Paths.get("uploads");
    private final String testFileName = "testFile.txt";

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

    @AfterEach
    @Override
    public void afterEach() {
        super.afterEach();
        deleteUploadedFiles();
    }

    @Test
    public void filesTabLayout() {
        homePage.goToHomePage(port);
        homePage.waitForLoaded();

        assertTrue(homePage.getFileUploadLabel().isDisplayed(), "File upload label was not displayed!");
        assertTrue(homePage.getFileUploadInput().isDisplayed(), "File upload input is not displayed!");
        assertTrue(homePage.getFileUploadButton().isDisplayed(), "File upload button is not displayed!");

        File fileToUpload = createTestFile();

        homePage.getFileUploadInput().sendKeys(fileToUpload.getAbsolutePath());
        homePage.getFileUploadButton().submit();
        assertTrue(homePage.getViewFileButton().isDisplayed(), "View file button was not displayed!");
        assertTrue(homePage.getDeleteFileButton().isDisplayed(), "Delete file button was not displayed!");
        List<WebElement> uploadedFiles = homePage.getFiles();
        assertEquals(1, uploadedFiles.size(), "There was an incorrect number of uploaded files displayed!");
        assertTrue(uploadedFiles.get(0).getText().contains(fileToUpload.getName()), "Incorrect file name was displayed!");

        homePage.getDeleteFileButton().submit();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        uploadedFiles = homePage.getFiles();
        assertEquals(0, uploadedFiles.size(), "Uploaded file is still visible after 'delete' button was clicked!");
    }

    private File createTestFile() {
        try {
            if (Files.notExists(pathToTestFiles)) {
                Files.createDirectory(pathToTestFiles);
            }

            File newFile = new File(pathToTestFiles.toString(), testFileName);
            newFile.createNewFile();
            return newFile;

        } catch (IOException e) {
            throw new FileException("Failed to create a file for test purposes", e);
        }
    }

    private void deleteUploadedFiles() {
        File file = fileUploadFolder.resolve(testFileName).toFile();
        if (file.delete()) {
            Logger.getGlobal().info("Test file deleted successfully");
        } else {
            Logger.getGlobal().severe(String.format("Failed to delete a test file: %s", testFileName));
        }
    }
}

package com.udacity.jwdnd.course1.cloudstorage.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SigninPage {

    private WebDriverWait wait;

    public SigninPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 1);
    }
}

package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pageobjects.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pageobjects.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pageobjects.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.pageobjects.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	int port;

	WebDriver driver;
	LoginPage loginPage;
	SignUpPage signUpPage;
	HomePage homePage;
	ResultPage resultPage;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		this.loginPage = new LoginPage(this.driver);
		this.signUpPage = new SignUpPage(this.driver);
		this.homePage = new HomePage(this.driver);
		this.resultPage = new ResultPage(this.driver);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	void exitResultPage() {
		resultPage.waitForLoaded();
		assertTrue(resultPage.getSuccessMessage().isDisplayed(), "Success message was not displayed!");

		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", resultPage.getGoToHomeLink());
		homePage.waitForLoaded();
	}
}

package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pageobjects.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pageobjects.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;
	private LoginPage loginPage;
	private SignUpPage signUpPage;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		this.loginPage = new LoginPage(this.driver);
		this.signUpPage = new SignUpPage(this.driver);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void loginPageLayout() {
		loginPage.goToLoginPage(this.port);
		assertEquals("Login", driver.getTitle());

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
		assertEquals("Sign Up", driver.getTitle());

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

}

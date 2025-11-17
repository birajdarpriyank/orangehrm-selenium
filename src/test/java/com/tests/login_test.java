package com.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pojo.base_class;
import com.pom.login_page;

@Listeners(com.listeners.test_listener.class)
public class login_test extends base_class {

	login_page log;

	@BeforeMethod
	public void setUp() {
		loadProperties();
		driver = initializebrowser(pro.getProperty("browser"));
		openApplication();
		log = new login_page(driver);
	}

	@Test
	public void verifyLogoTest() {
		Assert.assertTrue(log.isLogoVisible(), "Logo is NOT visible on the login page");
	}

	@Test(priority = 1)
	public void verifyTitleOfPage() {
		String actualPageTitle = getPageTitle();
		String expectedPageTitle = pro.getProperty("loginPageTitle");
		Assert.assertEquals(actualPageTitle, expectedPageTitle);
	}

	@Test
	public void verifyCurrentUrlOfPage() {
		String actualCurrentUrl = getCurrentUrl();
		String expectedCurrentUrl = pro.getProperty("url");
		Assert.assertEquals(actualCurrentUrl, expectedCurrentUrl);
	}

	@Test
	public void verifyDisplayedUsername() {
		String actualUsernameText = log.getDisplayedUsernameText();
		String expectedUsernameText = pro.getProperty("usernameDemo");
		Assert.assertEquals(actualUsernameText, expectedUsernameText);
	}

	@Test
	public void verifyDisplayedPassword() {
		String actualPasswordText = log.getDisplayedPasswordText();
		String expectedPasswordText = pro.getProperty("passwordDemo");
		Assert.assertEquals(actualPasswordText, expectedPasswordText);
	}

	@Test
	public void verifyDisplayedUsernameLabel() {
		String actualUsernameLabel = log.getDisplayedUsernameLabel();
		String expectedUsernameLabel = pro.getProperty("usernameLabel");
		Assert.assertEquals(actualUsernameLabel, expectedUsernameLabel);
	}

	@Test
	public void verifyDisplayedPasswordLabel() {
		String actualPasswordLabel = log.getDisplayedPasswordLabel();
		String expectedPasswordLabel = pro.getProperty("passLabel");
		Assert.assertEquals(actualPasswordLabel, expectedPasswordLabel);
	}

	@Test
	public void verifySuccessfullLogin() {
		String user = log.getDisplayedUsernameText().split(":")[1].trim();
		String pass = log.getDisplayedPasswordText().split(":")[1].trim();

		log.setUsernameInput(user);
		log.setPasswordInput(pass);
		log.clickOnSubmitButton();
		Assert.assertEquals(getCurrentUrl(), pro.getProperty("homePageUrl"), "Login redirection failed!");
	}

	@Test
	public void verifyLoginWithInvalidUsername() {
		String randomUser = RandomStringUtils.randomAlphabetic(8);
		String password = log.getDisplayedPasswordText().split(":")[1].trim();

		log.setUsernameInput(randomUser);
		log.setPasswordInput(password);
		log.clickOnSubmitButton();
		String errorMessage = log.getDisplayedErrorMessageUsername();
		Assert.assertEquals(errorMessage, pro.getProperty("errorMessageUsername"), "Error message not visible");
	}

	@Test
	public void verifyLoginWithInvalidPassword() {
		String username = log.getDisplayedUsernameText().split(":")[1].trim();
		String randomPass = RandomStringUtils.randomAlphanumeric(20);

		log.setUsernameInput(username);
		log.setPasswordInput(randomPass);
		log.clickOnSubmitButton();
		String errorMessage = log.getDisplayedErrorMessagePassword();
		Assert.assertEquals(errorMessage, pro.getProperty("errorMessagePassword"), "Error message not Visible!");
	}

	@Test
	public void verifyLoginWithInvalidCredentials() {
		String user = RandomStringUtils.randomAlphanumeric(10);
		String pass = RandomStringUtils.randomAlphabetic(8);

		log.setUsernameInput(user);
		log.setPasswordInput(pass);
		log.clickOnSubmitButton();
		String errorMessage = log.getDisplayedErrorMessageCommon();
		Assert.assertEquals(errorMessage, pro.getProperty("commonErrorMessage"), "Error message not visible");
	}

	@Test
	public void verifyLoginWithEmptyCredentials() {
		String user = "";
		String pass = "";

		log.setUsernameInput(user);
		log.setPasswordInput(pass);
		log.clickOnSubmitButton();
		String errorMessageUser = log.getDisplayedErrorMessageUsername();
		String errorMessagePass = log.getDisplayedErrorMessagePassword();
		Assert.assertEquals(errorMessageUser, pro.getProperty("errorMessageUsername"), "Error message not visible!");
		Assert.assertEquals(errorMessagePass, pro.getProperty("errorMessagePassword"), "Error message not visible!");
	}

	@Test
	public void verifyForgottenPasswordLink() {
		log.clickOnForgottenPasswordLink();
		Assert.assertEquals(getCurrentUrl(), pro.getProperty("forgottenPasswordUrl"));
	}

	@Test
	public void verifyDisplayedVersionText() {
		String versionText = log.getDisplayedVersionText();
		Assert.assertEquals(versionText, pro.getProperty("versionText"));
	}

	@Test
	public void verifyDisplayedCopyrightText() {
		String copyrightText = log.getDisplayedCopyrightText();
		Assert.assertEquals(copyrightText, pro.getProperty("copyrightText"));
	}

	@Test
	public void verifyOrangeHrmCopyrightHyperlink() {
		log.clickOnOrnageHrmHyperlink();
		Assert.assertEquals(getCurrentUrl(), pro.getProperty("copyright_page"));
	}

	@AfterMethod
	public void tearDown() {
		quitBrowser();
	}
}

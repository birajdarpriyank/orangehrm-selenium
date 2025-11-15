package com.tests;

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

	@Test(priority=1)
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
	public void verifyGetUsername() {
		String actualUsernameText = log.getDisplayedUsernameText();
		String expectedUsernameText = pro.getProperty("usernameDemo");
		Assert.assertEquals(actualUsernameText, expectedUsernameText);
	}

	@Test
	public void verifyGetPassword() {
		String actualPasswordText = log.getDisplayedPasswordText();
		String expectedPasswordText = pro.getProperty("passwordDemo");
		Assert.assertEquals(actualPasswordText, expectedPasswordText);
	}

	@Test
	public void verifyUsernameLabel() {
		String actualUsernameLabel = log.getDisplayedUsernameLabel();
		String expectedUsernameLabel = pro.getProperty("usernamelabel");
		Assert.assertEquals(actualUsernameLabel, expectedUsernameLabel);
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

	@AfterClass
	public void tearDown() {
		quitBrowser();
	}
}

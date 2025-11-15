package com.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pojo.base_class;
import com.utilities.wait_utility;

public class login_page {

	public WebDriver driver;
	public wait_utility wait;

	public login_page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new wait_utility(driver);
	}

	@FindBy(xpath = "//img[@alt='company-branding']")
	private WebElement logo;

	@FindBy(xpath = "//p[text()='Username : Admin']")
	private WebElement username;

	@FindBy(xpath = "//p[text()='Password : admin123']")
	private WebElement password;

	@FindBy(xpath = "//label[text()='Username']")
	private WebElement usernameLable;

	@FindBy(name = "username")
	private WebElement usernameInput;

	@FindBy(xpath = "//label[text()='Password']")
	private WebElement passwordLabel;

	@FindBy(name = "password")
	private WebElement passwordInput;

	@FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']")
	private WebElement submitButton;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']")
	private WebElement forgottenPasswordLink;

	@FindBy(xpath = "(//p[@class='oxd-text oxd-text--p orangehrm-copyright'])[1]")
	private WebElement versionText;

	@FindBy(xpath = "(//p[@class='oxd-text oxd-text--p orangehrm-copyright'])[2]")
	private WebElement copyrightText;

	public boolean isLogoVisible() {
		wait.waitForVisibility(logo);
		return logo.isDisplayed();
	}

	public String getDisplayedUsernameText() {
		return username.getText();
	}

	public String getDisplayedPasswordText() {
		return password.getText();
	}

	public String getDisplayedUsernameLabel() {
		wait.waitForVisibility(usernameLable);
		return usernameLable.getText();
	}

	public void setUsernameInput(String user) {
		usernameInput.sendKeys(user);
	}

	public String getDisplayedPasswordLabel() {
		return passwordLabel.getText();
	}

	public void setPasswordInput(String pass) {
		passwordInput.sendKeys(pass);
	}

	public home_page clickOnSubmitButton() {
		submitButton.click();
		return new home_page();
	}

	public forgotten_password_page clickOnForgottenPasswordLink() {
		forgottenPasswordLink.click();
		return new forgotten_password_page();
	}

	public String getDisplayedVersionText() {
		return versionText.getText();
	}

	public String getDisplayedCopyrightText() {
		return copyrightText.getText();
	}
}

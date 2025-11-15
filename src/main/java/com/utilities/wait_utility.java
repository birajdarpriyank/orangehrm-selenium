package com.utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class wait_utility {

	public WebDriver driver;
	public WebDriverWait wait;

	public wait_utility(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void waitForVisibility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForClickability(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForPresence(String path) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));
	}

	public void waitForTitle(String title) {
		wait.until(ExpectedConditions.titleIs(title));
	}

	public void waitForUrl(String url) {
		wait.until(ExpectedConditions.urlToBe(url));
	}

}

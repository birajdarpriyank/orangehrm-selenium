package com.utilities;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class screenshot_utility {

	public static String captureScreenshot(WebDriver driver, String testName) {
		try {
			TakesScreenshot take = ((TakesScreenshot) driver);
			File src = take.getScreenshotAs(OutputType.FILE);

			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
			String folder = System.getProperty("user.dir") + "/screenshots/";

			File dir = new File(folder);
			if (!dir.exists()) {
				dir.mkdir();
			}

			String filepath = folder + testName + " _ " + timestamp + ".png";
			Files.copy(src.toPath(), new File(filepath).toPath(), StandardCopyOption.REPLACE_EXISTING);

			return filepath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}

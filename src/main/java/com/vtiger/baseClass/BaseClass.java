package com.vtiger.baseClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.vtiger.genericUtility.FileUtility;
import com.vtiger.objRepo.LoginPage;

public class BaseClass {

	protected WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	public void setUp(@Optional("") String browserParameter) throws IOException {

		FileUtility fileUtility = new FileUtility();

		String browser = browserParameter;

		if (browser == null || browser.trim().isEmpty()) {
			browser = fileUtility.getDataFromProperties("browser");
		}

		String url = fileUtility.getDataFromProperties("url");
		String username = fileUtility.getDataFromProperties("username");
		String password = fileUtility.getDataFromProperties("password");

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();

		} else {
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(url);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {

		try {
			if (driver != null && isNegativeTest(result)) {

				Path folder = Paths.get("screenshots", "Negative");
				Files.createDirectories(folder);

				String className = result.getTestClass().getRealClass().getSimpleName();

				String methodName = result.getMethod().getMethodName();

				String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));

				String status;

				if (result.getStatus() == ITestResult.SUCCESS) {
					status = "PASS";
				} else if (result.getStatus() == ITestResult.SKIP) {
					status = "SKIP";
				} else {
					status = "FAIL";
				}

				String fileName = className + "_" + methodName + "_" + status + "_" + timestamp + ".png";

				Path destination = folder.resolve(fileName);

				Files.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath(), destination);

				System.out.println("Screenshot saved: " + destination.toAbsolutePath());
			}

		} catch (Exception e) {
			System.err.println("Screenshot capture failed: " + e.getMessage());

		} finally {
			if (driver != null) {
				driver.quit();
				driver = null;
			}
		}
	}

	private boolean isNegativeTest(ITestResult result) {

		for (String group : result.getMethod().getGroups()) {
			if ("Negative".equalsIgnoreCase(group)) {
				return true;
			}
		}

		return result.getTestClass().getRealClass().getSimpleName().toLowerCase().contains("negative");
	}
}

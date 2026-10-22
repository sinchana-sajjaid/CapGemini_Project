package com.vtiger.genericUtility;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

    // Wait for a webpage to load
    public void waitForPageLoad(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver ->
                        ((JavascriptExecutor) webDriver)
                                .executeScript("return document.readyState")
                                .equals("complete"));
    }

    // Wait until an element is visible
    public void waitForElementToBeVisible(
            WebDriver driver, WebElement element) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Wait until an element is clickable
    public void waitForElementToBeClickable(
            WebDriver driver, WebElement element) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Scroll to an element
    public void scrollToElement(
            WebDriver driver, WebElement element) {

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", element);
    }

    // Scroll to the bottom of the webpage
    public void scrollToBottom(WebDriver driver) {

        ((JavascriptExecutor) driver).executeScript(
                "window.scrollTo(0, document.body.scrollHeight);");
    }

    // Scroll to the top of the webpage
    public void scrollToTop(WebDriver driver) {

        ((JavascriptExecutor) driver).executeScript(
                "window.scrollTo(0, 0);");
    }

    // Get the webpage title
    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    // Get the current webpage URL
    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }
}



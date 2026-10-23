package com.vtiger.objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DashboardPage {
    private WebDriver driver;

    private By dashboardLink = By.xpath("//a[normalize-space()='Dashboard' or contains(@href,'module=Dashboard')]");
    private By editLink = By.xpath("//a[normalize-space()='Edit' or contains(@href,'pbss_edit=true')]");
    private By startDate = By.id("date_start");
    private By endDate = By.id("date_end");
    private By selectButton = By.xpath("//input[@type='submit' and @value='Select']");
    private By refreshLink = By.xpath("//a[normalize-space()='Refresh']");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openDashboard() {
        driver.findElement(dashboardLink).click();
    }

    public boolean isDashboardPageDisplayed() {
        String url = driver.getCurrentUrl();
        String text = getDashboardPageText().toLowerCase();

        return (url.contains("module=Home") || url.contains("module=Dashboard"))
                && !text.contains("fatal error")
                && !text.contains("access denied");
    }

    public String getDashboardPageText() {
        return driver.findElement(By.tagName("body")).getText();
    }

    public String getPageText() {
        return getDashboardPageText();
    }

    public boolean isChartDisplayed(String chartTitle) {
        return chartTitle != null
                && !chartTitle.trim().isEmpty()
                && getDashboardPageText().toLowerCase()
                        .contains(chartTitle.trim().toLowerCase());
    }

    public void clickEdit() {
        driver.findElement(editLink).click();
    }

    public boolean isEditDisplayed() {
        for (WebElement link : driver.findElements(editLink)) {
            if (link.isDisplayed()) return true;
        }
        return false;
    }

    public boolean isStartDateDisplayed() {
        return driver.findElements(startDate).size() > 0
                && driver.findElement(startDate).isDisplayed();
    }

    public boolean isEndDateDisplayed() {
        return driver.findElements(endDate).size() > 0
                && driver.findElement(endDate).isDisplayed();
    }

    public void clickRefresh() {
        driver.findElement(refreshLink).click();
    }

    public void enterStartDate(String date) {
        typeDate(startDate, date);
    }

    public void enterEndDate(String date) {
        typeDate(endDate, date);
    }

    private void typeDate(By locator, String date) {
        WebElement field = driver.findElement(locator);
        field.clear();
        field.sendKeys(date);
    }

    public String getStartDate() {
        return driver.findElement(startDate).getAttribute("value");
    }

    public String getEndDate() {
        return driver.findElement(endDate).getAttribute("value");
    }

    public void clickSelect() {
        driver.findElement(selectButton).click();
    }

    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript(
                "window.scrollTo(0, document.body.scrollHeight)");
    }

    public long getDashboardPageHeight() {
        return ((Number) ((JavascriptExecutor) driver).executeScript(
                "return document.body.scrollHeight")).longValue();
    }

    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }

    public boolean isRefreshDisplayed() {
        for (WebElement link : driver.findElements(refreshLink)) {
            if (link.isDisplayed()) return true;
        }
        return false;
    }
}

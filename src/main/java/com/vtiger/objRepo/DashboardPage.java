package com.vtiger.objRepo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage {

    WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    By dashboardLink = By.xpath("//a[contains(text(),'Dashboard')]");
    By editLink = By.xpath("//a[normalize-space(.)='Edit' and contains(@href,'pbss_edit=true')]");
    By startDate = By.id("date_start");
    By endDate = By.id("date_end");
    By selectButton = By.xpath("//input[@type='submit' and @value='Select']");
    By refreshLink = By.xpath("//a[normalize-space(.)='Refresh']");

    public void openDashboard() {
        if (!driver.getCurrentUrl().contains("module=Dashboard")) {
            driver.findElement(dashboardLink).click();
        }
    }

    public boolean isDashboardPageDisplayed() {
        return driver.getCurrentUrl().contains("module=Dashboard");
    }

    public String getDashboardPageText() {
        return driver.findElement(By.tagName("body")).getText();
    }

    public boolean isChartDisplayed(String chartTitle) {
        List<WebElement> charts = driver.findElements(
                By.xpath("//*[contains(normalize-space(.),\"" + chartTitle + "\")]")
        );

        for (WebElement chart : charts) {
            if (chart.isDisplayed() && chart.getText().contains(chartTitle)) {
                return true;
            }
        }
        return false;
    }

    public void clickEdit() {
        driver.findElement(editLink).click();
    }

    public boolean isEditDisplayed() {
        return driver.findElement(editLink).isDisplayed();
    }

    public boolean isStartDateDisplayed() {
        return driver.findElement(startDate).isDisplayed();
    }

    public void clickRefresh() {
        driver.findElement(refreshLink).click();
    }

    public void enterStartDate(String date) {
        WebElement field = driver.findElement(startDate);
        field.clear();
        field.sendKeys(date);
    }

    public void enterEndDate(String date) {
        WebElement field = driver.findElement(endDate);
        field.clear();
        field.sendKeys(date);
    }

    public String getStartDate() {
        return driver.findElement(startDate).getDomProperty("value");
    }

    public String getEndDate() {
        return driver.findElement(endDate).getDomProperty("value");
    }

    public void clickSelect() {
        driver.findElement(selectButton).click();
    }

    public String getPageText() {
        return driver.findElement(By.tagName("body")).getText();
    }
}

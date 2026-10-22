package com.vtiger.objRepo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpportunitiesPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By opportunityName =
            By.name("potentialname");

    private final By amount =
            By.name("amount");

    private final By salesStage =
            By.name("sales_stage");

    private final By saveButton =
            By.cssSelector("input.crmbutton.small.save");

    public OpportunitiesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(15));
    }

    public void enterOpportunityName(String name) {
        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        opportunityName));
        field.clear();
        field.sendKeys(name);
    }

    public void enterAmount(String value) {
        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        amount));
        field.clear();
        field.sendKeys(value);
    }

    public String getAmount() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        amount)).getAttribute("value");
    }

    public void selectSalesStage(String stage) {
        WebElement field = wait.until(
                ExpectedConditions.elementToBeClickable(
                        salesStage));
        new Select(field).selectByVisibleText(stage);
    }

    public String getSalesStage() {
        return new Select(
                wait.until(ExpectedConditions
                        .visibilityOfElementLocated(salesStage)))
                .getFirstSelectedOption().getText().trim();
    }

    public void saveOpportunity() {
        wait.until(
                ExpectedConditions.elementToBeClickable(saveButton))
                .click();
    }
}


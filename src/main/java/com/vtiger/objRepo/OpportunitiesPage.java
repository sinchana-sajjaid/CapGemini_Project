package com.vtiger.objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class OpportunitiesPage {
    private WebDriver driver;

    private By opportunityName = By.name("potentialname");
    private By amount = By.name("amount");
    private By salesStage = By.name("sales_stage");
    private By saveButton = By.cssSelector("input.crmbutton.small.save");

    public OpportunitiesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterOpportunityName(String name) {
        driver.findElement(opportunityName).clear();
        driver.findElement(opportunityName).sendKeys(name);
    }

    public void enterAmount(String value) {
        driver.findElement(amount).clear();
        driver.findElement(amount).sendKeys(value);
    }

    public String getAmount() {
        return driver.findElement(amount).getAttribute("value");
    }

    public void selectSalesStage(String stage) {
        new Select(driver.findElement(salesStage)).selectByVisibleText(stage);
    }

    public String getSalesStage() {
        return new Select(driver.findElement(salesStage))
                .getFirstSelectedOption().getText().trim();
    }

    public void saveOpportunity() {
        driver.findElement(saveButton).click();
    }
}

package com.vtiger.objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CampaignPage {
    private WebDriver driver;

    private By moreButton = By.xpath("//a[contains(normalize-space(.),'More')]");
    private By campaignsLink = By.xpath("//a[normalize-space()='Campaigns' or contains(@href,'module=Campaigns')]");
    private By createButton = By.xpath("//img[@title='Create Campaign...' or contains(@alt,'Create Campaign')]");
    private By campaignName = By.name("campaignname");
    private By assignedTo = By.name("assigned_user_id");
    private By campaignStatus = By.name("campaignstatus");
    private By campaignType = By.name("campaigntype");
    private By closingDate = By.name("closingdate");
    private By saveButton = By.cssSelector("input.crmbutton.small.save");

    public CampaignPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCampaigns() {
        if (driver.getCurrentUrl().contains("module=Campaigns")) return;

        if (driver.findElements(campaignsLink).size() > 0
                && driver.findElement(campaignsLink).isDisplayed()) {
            driver.findElement(campaignsLink).click();
        } else {
            driver.findElement(moreButton).click();
            driver.findElement(campaignsLink).click();
        }
    }

    public void clickCreateCampaign() {
        driver.findElement(createButton).click();
    }

    public void enterCampaignName(String value) { type(campaignName, value); }
    public void enterClosingDate(String value) { type(closingDate, value); }

    private void type(By locator, String value) {
        if (value != null && !value.trim().isEmpty()) {
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(value.trim());
        }
    }

    public void selectAssignedTo(String value) { selectOption(assignedTo, value); }
    public void selectCampaignStatus(String value) { selectOption(campaignStatus, value); }
    public void selectCampaignType(String value) { selectOption(campaignType, value); }

    private void selectOption(By locator, String value) {
        if (value != null && !value.trim().isEmpty()) {
            new Select(driver.findElement(locator)).selectByVisibleText(value.trim());
        }
    }

    public void clearAssignedTo() {
        new Select(driver.findElement(assignedTo)).selectByIndex(0);
    }

    public void clickSave() {
        driver.findElement(saveButton).click();
    }

    public boolean isCampaignNameFieldDisplayed() {
        return driver.findElement(campaignName).isDisplayed();
    }
}

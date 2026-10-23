package com.vtiger.objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class LeadsPage {
    private WebDriver driver;

    private By createLeadButton = By.xpath("//img[@title='Create Lead...' or contains(@alt,'Create Lead')]");
    private By firstName = By.name("firstname");
    private By lastName = By.name("lastname");
    private By company = By.name("company");
    private By phone = By.id("phone");
    private By email = By.id("email");
    private By assignedTo = By.name("assigned_user_id");
    private By city = By.name("city");
    private By state = By.name("state");
    private By postalCode = By.name("code");
    private By country = By.name("country");
    private By saveButton = By.cssSelector("input.crmbutton.small.save");

    public LeadsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCreateLead() {
        driver.findElement(createLeadButton).click();
    }

    public void enterFirstName(String value) { type(firstName, value); }
    public void enterLastName(String value) { type(lastName, value); }
    public void enterCompany(String value) { type(company, value); }
    public void enterPhone(String value) { type(phone, value); }
    public void enterEmail(String value) { type(email, value); }
    public void enterCity(String value) { type(city, value); }
    public void enterState(String value) { type(state, value); }
    public void enterPostalCode(String value) { type(postalCode, value); }
    public void enterCountry(String value) { type(country, value); }

    private void type(By locator, String value) {
        if (value != null && !value.trim().isEmpty()) {
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(value.trim());
        }
    }

    public void selectAssignedTo(String value) {
        if (value != null && !value.trim().isEmpty()) {
            new Select(driver.findElement(assignedTo)).selectByVisibleText(value.trim());
        }
    }

    public void clearAssignedTo() {
        new Select(driver.findElement(assignedTo)).selectByIndex(0);
    }

    public void clickSave() {
        driver.findElement(saveButton).click();
    }

    public boolean isFirstNameFieldDisplayed() {
        return driver.findElement(firstName).isDisplayed();
    }
}

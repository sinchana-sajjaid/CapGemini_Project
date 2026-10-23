package com.vtiger.objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    private By leadsLink = By.xpath("//a[normalize-space()='Leads' or contains(@href,'module=Leads')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLeads() {
        driver.findElement(leadsLink).click();
    }
}

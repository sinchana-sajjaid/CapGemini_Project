package com.vtiger.objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage {

    WebDriver driver;

    public CampaignPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'More')]")
    private WebElement moreButton;

    @FindBy(xpath = "//a[normalize-space()='Campaigns']")
    private WebElement campaignsLink;

    @FindBy(xpath = "//img[@title='Create Campaign...']")
    private WebElement createCampaignButton;

    @FindBy(name = "campaignname")
    private WebElement campaignName;

    @FindBy(xpath = "//select[@name='assigned_user_id']")
    private WebElement assignedTo;

    @FindBy(name = "campaignstatus")
    private WebElement campaignStatus;

    @FindBy(name = "campaigntype")
    private WebElement campaignType;

    @FindBy(name = "closingdate")
    private WebElement closingDate;

    @FindBy(css = "input.crmbutton.small.save")
    private WebElement saveButton;


    public void clickCampaigns() {
        moreButton.click();
        campaignsLink.click();
    }

    public void clickCreateCampaign() {
        createCampaignButton.click();
    }

    public void enterCampaignName(String value) {
        if (value != null && !value.trim().isEmpty()) {
            campaignName.clear();
            campaignName.sendKeys(value);
        }
    }

    public void selectAssignedTo(String value) {

        if (value != null && !value.trim().isEmpty()) {

            org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(assignedTo);

            for (WebElement option : select.getOptions()) {

                if (option.getText().trim()
                        .equalsIgnoreCase(value.trim())) {

                    select.selectByValue(
                            option.getAttribute("value")
                    );

                    return;
                }
            }

            throw new RuntimeException(
                    "Assigned To option not found: " + value
            );
        }
    }

    public void selectCampaignStatus(String value) {

        if (value != null && !value.trim().isEmpty()) {

            org.openqa.selenium.support.ui.Select select =
                    new org.openqa.selenium.support.ui.Select(campaignStatus);

            for (WebElement option : select.getOptions()) {

                if (option.getText().trim()
                        .equalsIgnoreCase(value.trim())) {

                    select.selectByValue(
                            option.getAttribute("value")
                    );

                    return;
                }
            }

            throw new RuntimeException(
                    "Campaign Status option not found: " + value
            );
        }
    }

    public void selectCampaignType(String value) {

        if (value != null && !value.trim().isEmpty()) {

        org.openqa.selenium.support.ui.Select select =
                new org.openqa.selenium.support.ui.Select(campaignType);

        for (WebElement option : select.getOptions()) {

            String optionText = option.getText().trim();

            if (optionText.equalsIgnoreCase(value.trim())) {
                select.selectByValue(option.getAttribute("value"));
                return;
            }
        }

        StringBuilder availableOptions = new StringBuilder();

        System.out.println("Available campaign types:");

        for (WebElement option : select.getOptions()) {
            String optionText = option.getText().trim();

            System.out.println(optionText);
            availableOptions.append(optionText).append(", ");
        }

        throw new RuntimeException(
                "Campaign Type option not found: " + value
                + ". Available options: " + availableOptions
        );
    }
}



    public void enterClosingDate(String value) {

        if (value != null && !value.trim().isEmpty()) {
            closingDate.clear();
            closingDate.sendKeys(value);
        }
    }

    public void clickSave() {
        saveButton.click();
    }
    public void clearAssignedTo() {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
            "var select = document.getElementsByName('assigned_user_id')[0];" +
            "select.value = '';" +
            "select.dispatchEvent(new Event('change'));"
        );
    }
}

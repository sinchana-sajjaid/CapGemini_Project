package com.vtiger.objRepo;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LeadsPage {

    WebDriver driver;

    public LeadsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//img[@title='Create Lead...']")
    private WebElement createLeadButton;

    @FindBy(name = "firstname")
    private WebElement firstName;

    @FindBy(name = "lastname")
    private WebElement lastName;

    @FindBy(name = "company")
    private WebElement company;

    @FindBy(id = "phone")
    private WebElement phone;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(xpath = "//select[@name='assigned_user_id']")
    private WebElement assignedTo;

    @FindBy(name = "city")
    private WebElement city;

    @FindBy(name = "state")
    private WebElement state;

    @FindBy(name = "code")
    private WebElement postalCode;

    @FindBy(name = "country")
    private WebElement country;

    @FindBy(css = "input.crmbutton.small.save")
    private WebElement saveButton;


    public void clickCreateLead() {
        createLeadButton.click();
    }


    public void enterFirstName(String value) {

        if (value != null && !value.trim().isEmpty()) {
            firstName.clear();
            firstName.sendKeys(value.trim());
        }
    }


    public void enterLastName(String value) {

        if (value != null && !value.trim().isEmpty()) {
            lastName.clear();
            lastName.sendKeys(value.trim());
        }
    }


    public void enterCompany(String value) {

        if (value != null && !value.trim().isEmpty()) {
            company.clear();
            company.sendKeys(value.trim());
        }
    }


    public void enterPhone(String value) {

        if (value != null && !value.trim().isEmpty()) {
            phone.clear();
            phone.sendKeys(value.trim());
        }
    }


    public void enterEmail(String value) {

        if (value != null && !value.trim().isEmpty()) {
            email.clear();
            email.sendKeys(value.trim());
        }
    }


    public void selectAssignedTo(String value) {

        if (value == null || value.trim().isEmpty()) {
            return;
        }

        Select select = new Select(assignedTo);

        String expectedValue = value.trim();

        System.out.println("Assigned To from Excel: [" + expectedValue + "]");

        for (WebElement option : select.getOptions()) {

            String optionText = option
                    .getAttribute("textContent")
                    .trim();

            String optionValue = option
                    .getAttribute("value");

            System.out.println(
                    "Option Text: [" + optionText +
                    "] | Option Value: [" + optionValue + "]"
            );

            if (optionText.equalsIgnoreCase(expectedValue)) {

                select.selectByValue(optionValue);

                System.out.println(
                        "Assigned To selected: " + optionText
                );

                return;
            }
        }

        throw new RuntimeException(
                "Assigned To option not found: " + expectedValue
        );
    }


    public void enterCity(String value) {

        if (value != null && !value.trim().isEmpty()) {
            city.clear();
            city.sendKeys(value.trim());
        }
    }


    public void enterState(String value) {

        if (value != null && !value.trim().isEmpty()) {
            state.clear();
            state.sendKeys(value.trim());
        }
    }


    public void enterPostalCode(String value) {

        if (value != null && !value.trim().isEmpty()) {
            postalCode.clear();
            postalCode.sendKeys(value.trim());
        }
    }


    public void enterCountry(String value) {

        if (value != null && !value.trim().isEmpty()) {
            country.clear();
            country.sendKeys(value.trim());
        }
    }


    public void clickSave() {
        saveButton.click();
    }
    public void clearAssignedTo() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
            "var element = document.getElementsByName('assigned_user_id')[0];" +
            "element.selectedIndex = -1;" +
            "element.value = '';" +
            "element.dispatchEvent(new Event('change'));"
        );
    }
}

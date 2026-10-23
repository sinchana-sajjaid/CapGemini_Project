package com.vtiger.objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class TroubleTicketsPage {
    private WebDriver driver;

    private By moreMenu = By.xpath("//a[contains(normalize-space(.),'More')]");
    private By ticketsLink = By.xpath("//a[normalize-space()='Trouble Tickets' or contains(@href,'module=HelpDesk')]");
    private By createButton = By.xpath("//img[contains(@title,'Create') or contains(@alt,'Create')]");
    private By ticketTitle = By.name("ticket_title");
    private By assignedTo = By.name("assigned_user_id");
    private By priority = By.name("ticketpriorities");
    private By severity = By.name("ticketseverities");
    private By status = By.name("ticketstatus");
    private By category = By.name("ticketcategories");
    private By description = By.name("description");
    private By saveButton = By.cssSelector("input.crmbutton.small.save");

    public TroubleTicketsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickTroubleTickets() {
        if (driver.getCurrentUrl().contains("module=HelpDesk")) return;

        if (driver.findElements(ticketsLink).size() > 0
                && driver.findElement(ticketsLink).isDisplayed()) {
            driver.findElement(ticketsLink).click();
        } else {
            driver.findElement(moreMenu).click();
            driver.findElement(ticketsLink).click();
        }
    }

    public void clickCreateTicket() {
        driver.findElement(createButton).click();
    }

    public void enterTicketTitle(String value) { type(ticketTitle, value); }
    public void enterDescription(String value) { type(description, value); }

    private void type(By locator, String value) {
        if (value != null && !value.trim().isEmpty()) {
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(value.trim());
        }
    }

    public void selectAssignedTo(String value) { selectOption(assignedTo, value); }
    public void selectPriority(String value) { selectOption(priority, value); }
    public void selectSeverity(String value) { selectOption(severity, value); }
    public void selectStatus(String value) { selectOption(status, value); }
    public void selectCategory(String value) { selectOption(category, value); }

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
}

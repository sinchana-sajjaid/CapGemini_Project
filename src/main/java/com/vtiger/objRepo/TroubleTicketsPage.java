package com.vtiger.objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TroubleTicketsPage {

    WebDriver driver;

    public TroubleTicketsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(normalize-space(.),'More')]")
    private WebElement moreMenu;

    @FindBy(xpath = "//a[normalize-space(.)='Trouble Tickets']")
    private WebElement troubleTicketsLink;

    @FindBy(xpath = "//img[contains(@title,'Create') or contains(@alt,'Create')]")
    private WebElement createTicketButton;

    @FindBy(name = "ticket_title")
    private WebElement ticketTitle;

    @FindBy(xpath = "//select[@name='assigned_user_id']")
    private WebElement assignedTo;

    @FindBy(name = "ticketpriorities")
    private WebElement priority;

    @FindBy(name = "ticketseverities")
    private WebElement severity;

    @FindBy(name = "ticketstatus")
    private WebElement status;

    @FindBy(name = "ticketcategories")
    private WebElement category;

    @FindBy(name = "description")
    private WebElement description;

    @FindBy(css = "input.crmbutton.small.save")
    private WebElement saveButton;


    public void clickTroubleTickets() {

        moreMenu.click();

        troubleTicketsLink.click();
    }


    public void clickCreateTicket() {

        createTicketButton.click();
    }


    public void enterTicketTitle(String value) {

        if (value != null && !value.trim().isEmpty()) {

            ticketTitle.clear();
            ticketTitle.sendKeys(value);
        }
    }


    public void selectAssignedTo(String value) {

        if (value != null && !value.trim().isEmpty()) {

            Select select = new Select(assignedTo);

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


    public void selectPriority(String value) {

        if (value != null && !value.trim().isEmpty()) {

            Select select = new Select(priority);

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
                    "Priority option not found: " + value
            );
        }
    }


    public void selectSeverity(String value) {

        if (value != null && !value.trim().isEmpty()) {

            Select select = new Select(severity);

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
                    "Severity option not found: " + value
            );
        }
    }


    public void selectStatus(String value) {

        if (value != null && !value.trim().isEmpty()) {

            Select select = new Select(status);

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
                    "Status option not found: " + value
            );
        }
    }


    public void selectCategory(String value) {

        if (value != null && !value.trim().isEmpty()) {

            Select select = new Select(category);

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
                    "Category option not found: " + value
            );
        }
    }


    public void enterDescription(String value) {

        if (value != null && !value.trim().isEmpty()) {

            description.clear();
            description.sendKeys(value);
        }
    }


    public void clickSave() {

        saveButton.click();
    }
}
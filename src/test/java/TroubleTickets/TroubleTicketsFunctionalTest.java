package TroubleTickets;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.TroubleTicketsPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TroubleTicketsFunctionalTest extends BaseClass {

    @Test(groups = {"Functional", "EP"})
    public void verifyValidTicketCreation() throws Exception {

        ExcelUtility excel = new ExcelUtility();

        String testId = "TT_002";

        TroubleTicketsPage ticketPage =
                new TroubleTicketsPage(driver);

        ticketPage.clickTroubleTickets();
        ticketPage.clickCreateTicket();

        ticketPage.enterTicketTitle(
                excel.getDataByTestId(
                        "TroubleTicketsData",
                        testId,
                        "TicketTitle"));

        ticketPage.selectAssignedTo(
                excel.getDataByTestId(
                        "TroubleTicketsData",
                        testId,
                        "AssignedTo"));

        ticketPage.selectPriority(
                excel.getDataByTestId(
                        "TroubleTicketsData",
                        testId,
                        "Priority"));

        ticketPage.selectSeverity(
                excel.getDataByTestId(
                        "TroubleTicketsData",
                        testId,
                        "Severity"));

        ticketPage.selectStatus(
                excel.getDataByTestId(
                        "TroubleTicketsData",
                        testId,
                        "Status"));

        ticketPage.selectCategory(
                excel.getDataByTestId(
                        "TroubleTicketsData",
                        testId,
                        "Category"));

        ticketPage.enterDescription(
                excel.getDataByTestId(
                        "TroubleTicketsData",
                        testId,
                        "Description"));

        ticketPage.clickSave();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("DetailView"),
                "Trouble Ticket was not created"
        );

        Reporter.log(
                "Trouble Ticket created successfully",
                true
        );
    }
}
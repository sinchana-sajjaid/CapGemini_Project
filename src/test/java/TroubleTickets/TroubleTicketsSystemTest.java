package TroubleTickets;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.TroubleTicketsPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TroubleTicketsSystemTest extends BaseClass {

    @Test(groups = "System")
    public void verifyHighPriorityTicket() throws Exception {

        ExcelUtility excel = new ExcelUtility();

        String testId = "TT_011";

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
                "High Priority Ticket was not created"
        );

        Reporter.log(
                "High Priority Ticket created successfully",
                true
        );
    }


    @Test(groups = "System")
    public void verifyCriticalSeverityTicket() throws Exception {

        ExcelUtility excel = new ExcelUtility();

        String testId = "TT_012";

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
                "Critical Severity Ticket was not created"
        );

        Reporter.log(
                "Critical Severity Ticket created successfully",
                true
        );
    }
}
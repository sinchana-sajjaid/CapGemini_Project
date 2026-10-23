package TroubleTickets;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.TroubleTicketsPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TroubleTicketsBVATest extends BaseClass {

    @Test(groups = {"BVA", "Functional"})
    public void verifyMinimumTicketTitle() throws Exception {

        ExcelUtility excel = new ExcelUtility();

        String testId = "TT_007";

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

        ticketPage.clickSave();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("DetailView"),
                "Minimum Ticket Title was not accepted"
        );

        Reporter.log(
                "Minimum Ticket Title accepted successfully",
                true
        );
    }


    @Test(groups = {"BVA", "Functional"})
    public void verifyLongTicketTitle() throws Exception {

        ExcelUtility excel = new ExcelUtility();

        String testId = "TT_008";

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

        ticketPage.clickSave();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("DetailView"),
                "Long Ticket Title was not accepted"
        );

        Reporter.log(
                "Long Ticket Title accepted successfully",
                true
        );
    }
}


   
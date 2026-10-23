package TroubleTickets;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.TroubleTicketsPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TroubleTicketsSmokeTest extends BaseClass {

    @Test(groups = "Smoke")
    public void verifyTroubleTicketsCreatePage() throws Exception {

        ExcelUtility excel = new ExcelUtility();

        String testId = "TT_001";

        TroubleTicketsPage ticketPage =
                new TroubleTicketsPage(driver);

        ticketPage.clickTroubleTickets();

        ticketPage.clickCreateTicket();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("HelpDesk"),
                "Trouble Tickets Create page was not opened"
        );

        Reporter.log(
                excel.getDataByTestId(
                        "TroubleTicketsData",
                        testId,
                        "Description"),
                true
        );
    }
}
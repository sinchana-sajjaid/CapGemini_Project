package TroubleTickets;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.TroubleTicketsPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TroubleTicketsNegativeTest extends BaseClass {

    @Test(groups = {"Negative", "EP"})
    public void verifyTicketWithoutTitle() throws Exception {

        ExcelUtility excel = new ExcelUtility();

        String testId = "TT_003";

        String expectedMessage =
                excel.getDataByTestId(
                        "TroubleTicketsData",
                        testId,
                        "ExpectedMessage");

        TroubleTicketsPage ticketPage =
                new TroubleTicketsPage(driver);

        ticketPage.clickTroubleTickets();
        ticketPage.clickCreateTicket();

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

        String actualMessage =
                driver.switchTo().alert().getText();

        Assert.assertEquals(
                actualMessage,
                expectedMessage,
                "Validation message is incorrect"
        );

        Reporter.log(
                "Expected: " + expectedMessage,
                true
        );

        Reporter.log(
                "Actual: " + actualMessage,
                true
        );

        driver.switchTo().alert().accept();
    }

}

package dashboard;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.DashboardPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class DashboardNegativeTest extends BaseClass {

    @Test(groups = {"Negative"})
    public void verifyInvalidDateRangeTest() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String id = "DASH-009";

        DashboardPage page = new DashboardPage(driver);
        page.openDashboard();
        page.clickEdit();

        page.enterStartDate(
                excel.getDashboardDataByTestId("DashboardData", id, "StartDate"));
        page.enterEndDate(
                excel.getDashboardDataByTestId("DashboardData", id, "EndDate"));

        page.clickSelect();

        String alertText = "";

        try {
            Alert alert = driver.switchTo().alert();
            alertText = alert.getText();
            alert.accept();
        } catch (NoAlertPresentException ignored) {
        }

        String text = page.getDashboardPageText().toLowerCase();

        boolean validationShown = !alertText.trim().isEmpty()
                || text.contains("invalid date")
                || text.contains("date range")
                || text.contains("start date must")
                || text.contains("end date must");

        Assert.assertTrue(validationShown,
                "The reversed date range was not rejected with visible validation.");

        Reporter.log(
                excel.getDashboardDataByTestId("DashboardData", id, "ExpectedResult"),
                true);
    }
}


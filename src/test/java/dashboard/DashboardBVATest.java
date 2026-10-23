package dashboard;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.DashboardPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class DashboardBVATest extends BaseClass {

    private DashboardPage openChartFilters() {
        DashboardPage page = new DashboardPage(driver);
        page.openDashboard();
        page.clickEdit();
        return page;
    }

    @Test(groups = {"BVA", "Functional", "Positive"})
    public void verifyValidDateRangeTest() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String id = "DASH-008";

        String start = excel.getDashboardDataByTestId("DashboardData", id, "StartDate");
        String end = excel.getDashboardDataByTestId("DashboardData", id, "EndDate");

        DashboardPage page = openChartFilters();

        page.enterStartDate(start);
        page.enterEndDate(end);

        Assert.assertEquals(page.getStartDate(), start);
        Assert.assertEquals(page.getEndDate(), end);

        page.clickSelect();

        Assert.assertFalse(
                page.getDashboardPageText().toLowerCase().contains("fatal error"),
                "Dashboard failed after applying a valid date range."
        );

        Reporter.log(
                excel.getDashboardDataByTestId("DashboardData", id, "ExpectedResult"),
                true
        );
    }
}

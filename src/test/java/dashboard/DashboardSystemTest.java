package dashboard;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.DashboardPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class DashboardSystemTest extends BaseClass {

    @Test(groups = {"System"})
    public void verifyDashboardRefreshControl() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        DashboardPage page = new DashboardPage(driver);

        page.openDashboard();

        String before = page.getDashboardPageText();
        Assert.assertFalse(before.trim().isEmpty(),
                "Dashboard is blank before refresh.");

        if (page.isRefreshDisplayed()) {
            page.clickRefresh();
        } else {
            driver.navigate().refresh();
        }

        Assert.assertTrue(page.isDashboardPageDisplayed(),
                "Dashboard did not remain available after refresh.");

        Assert.assertFalse(page.getDashboardPageText().toLowerCase().contains("fatal error"),
                "Fatal error appeared after dashboard refresh.");

        Reporter.log(excel.getDashboardDataByTestId(
                "DashboardData", "DASH-007", "Description"), true);
    }

    @Test(groups = {"System"})
    public void verifyDashboardNoDataDateRangeState() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        DashboardPage page = new DashboardPage(driver);

        page.openDashboard();
        page.clickEdit();

        page.enterStartDate(excel.getDashboardDataByTestId(
                "DashboardData", "DASH-011", "StartDate"));

        page.enterEndDate(excel.getDashboardDataByTestId(
                "DashboardData", "DASH-011", "EndDate"));

        page.clickSelect();

        String text = page.getDashboardPageText().toLowerCase();

        Assert.assertFalse(
                text.contains("fatal error") || text.contains("internal server error"),
                "Dashboard showed an application error for an empty-data range.");

        Reporter.log(excel.getDashboardDataByTestId(
                "DashboardData", "DASH-011", "ExpectedResult"), true);
    }
}

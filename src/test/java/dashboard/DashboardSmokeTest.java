
package dashboard;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.DashboardPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class DashboardSmokeTest extends BaseClass {

    @Test(groups = {"Smoke"})
    public void verifyDashboardLoadsTest() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        DashboardPage page = new DashboardPage(driver);
        page.openDashboard();

        Assert.assertTrue(page.isDashboardPageDisplayed(),
                excel.getDashboardDataByTestId("DashboardData", "DASH-001", "ExpectedResult"));

        Assert.assertFalse(page.getDashboardPageText().toLowerCase().contains("fatal error"),
                "Dashboard displayed a fatal error.");

        Reporter.log("Dashboard loaded successfully.", true);
    }

    @Test(groups = {"Smoke"})
    public void verifyDashboardNavigationTest() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        DashboardPage page = new DashboardPage(driver);

        driver.get("http://49.249.29.4:8888/index.php?module=Potentials&action=index");
        page.openDashboard();

        Assert.assertTrue(page.isDashboardPageDisplayed(),
                "Navigation to Dashboard failed.");

        String chartTitle = excel.getDashboardDataByTestId(
                "DashboardData", "DASH-014", "ChartTitle");

        Assert.assertTrue(page.isChartDisplayed(chartTitle),
                "Core Dashboard widget did not load after navigation.");

        Reporter.log(excel.getDashboardDataByTestId(
                "DashboardData", "DASH-014", "ExpectedResult"), true);
    }
}

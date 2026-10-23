package dashboard;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.DashboardPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class DashboardIntegrationTest extends BaseClass {

    @Test(groups = {"Integration"})
    public void verifyOpportunityModuleCanReturnToDashboard() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        DashboardPage page = new DashboardPage(driver);

        String id = "DASH-012";
        String chartTitle = excel.getDashboardDataByTestId(
                "DashboardData", id, "ChartTitle");

        driver.get("http://49.249.29.4:8888/index.php?module=Potentials&action=index");

        Assert.assertTrue(driver.getCurrentUrl().contains("module=Potentials"),
                "Opportunities module did not open.");

        page.openDashboard();

        Assert.assertTrue(page.isDashboardPageDisplayed(),
                "Dashboard did not open.");

        Assert.assertTrue(page.isChartDisplayed(chartTitle),
                "Pipeline chart is not displayed.");

        Reporter.log(excel.getDashboardDataByTestId(
                "DashboardData", id, "ExpectedResult"), true);
    }

    @Test(groups = {"Integration"})
    public void verifyLeadsModuleCanReturnToDashboard() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        DashboardPage page = new DashboardPage(driver);

        String id = "DASH-013";
        String chartTitle = excel.getDashboardDataByTestId(
                "DashboardData", id, "ChartTitle");

        driver.get("http://49.249.29.4:8888/index.php?module=Leads&action=index");

        Assert.assertTrue(driver.getCurrentUrl().contains("module=Leads"),
                "Leads module did not open.");

        page.openDashboard();

        Assert.assertTrue(page.isDashboardPageDisplayed(),
                "Dashboard did not open.");

        Assert.assertTrue(page.isChartDisplayed(chartTitle),
                "Lead chart is not displayed.");

        Reporter.log(excel.getDashboardDataByTestId(
                "DashboardData", id, "ExpectedResult"), true);
    }
}


package dashboard;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.DashboardPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class DashboardFunctionalTest extends BaseClass {

    @Test(groups = {"Functional", "Positive"})
    public void verifyDashboardChartsTest() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        DashboardPage page = new DashboardPage(driver);
        page.openDashboard();

        String pipelineChart = excel.getDashboardDataByTestId(
                "DashboardData", "DASH-002", "ChartTitle");

        String leadsChart = excel.getDashboardDataByTestId(
                "DashboardData", "DASH-004", "ChartTitle");

        Assert.assertTrue(page.isChartDisplayed(pipelineChart),
                "Pipeline chart is not displayed.");

        Assert.assertTrue(page.isChartDisplayed(leadsChart),
                "Leads chart is not displayed.");

        Reporter.log("Dashboard charts were displayed.", true);
    }

    @Test(groups = {"Functional", "Positive"})
    public void verifyDashboardEditControlsTest() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        DashboardPage page = new DashboardPage(driver);

        page.openDashboard();
        page.clickEdit();

        Assert.assertTrue(driver.getCurrentUrl().contains("pbss_edit=true"),
                "Dashboard configuration did not open.");

        Assert.assertTrue(page.isStartDateDisplayed(),
                "Start-date field is not displayed.");

        Reporter.log(excel.getDashboardDataByTestId(
                "DashboardData", "DASH-017", "Description"), true);
    }

    @Test(groups = {"Functional", "Positive"})
    public void verifyPipelineByMonthWidget() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        DashboardPage page = new DashboardPage(driver);

        page.openDashboard();

        String title = excel.getDashboardDataByTestId(
                "DashboardData", "DASH-003", "ChartTitle");

        Assert.assertTrue(page.isChartDisplayed(title),
                "Pipeline By Month By Outcome widget is not displayed.");

        Reporter.log(excel.getDashboardDataByTestId(
                "DashboardData", "DASH-003", "Description"), true);
    }
}

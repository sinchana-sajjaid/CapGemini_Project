package Campaign;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.CampaignPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CampaignIntegrationTest extends BaseClass {

    @Test(groups = {"Integration"})
    public void verifyCampaignNavigationAndCreationIntegration() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String id = "CM_013";
        CampaignPage page = new CampaignPage(driver);

        page.clickCampaigns();
        Assert.assertTrue(driver.getCurrentUrl().contains("Campaign"),
                "Campaign module navigation failed.");

        page.clickCreateCampaign();
        page.enterCampaignName(excel.getDataByTestId("CampaignData", id, "CampaignName"));
        page.selectAssignedTo(excel.getDataByTestId("CampaignData", id, "AssignedTo"));
        page.selectCampaignStatus(excel.getDataByTestId("CampaignData", id, "Status"));
        page.selectCampaignType(excel.getDataByTestId("CampaignData", id, "Type"));
        page.enterClosingDate(excel.getDataByTestId("CampaignData", id, "ClosingDate"));
        page.clickSave();

        Assert.assertTrue(driver.getCurrentUrl().contains("DetailView"),
                "Campaign creation integration failed.");

        Reporter.log(excel.getDataByTestId("CampaignData", id, "Description"), true);
        Reporter.log("Campaign navigation and creation integration verified.", true);
    }
}

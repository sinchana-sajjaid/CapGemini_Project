package Campaign;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.CampaignPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CampaignBVATest extends BaseClass {

    private void createCampaign(String id) throws Exception {
        ExcelUtility excel = new ExcelUtility();
        CampaignPage page = new CampaignPage(driver);

        page.clickCampaigns();
        page.clickCreateCampaign();
        page.enterCampaignName(excel.getDataByTestId("CampaignData", id, "CampaignName"));
        page.selectAssignedTo(excel.getDataByTestId("CampaignData", id, "AssignedTo"));
        page.selectCampaignStatus(excel.getDataByTestId("CampaignData", id, "Status"));
        page.selectCampaignType(excel.getDataByTestId("CampaignData", id, "Type"));
        page.enterClosingDate(excel.getDataByTestId("CampaignData", id, "ClosingDate"));
        page.clickSave();
    }

    @Test(groups = {"BVA", "Functional"})
    public void verifyMinimumCampaignName() throws Exception {
        String id = "CM_007";
        createCampaign(id);
        Assert.assertTrue(driver.getCurrentUrl().contains("DetailView"),
                "Minimum Campaign Name was not accepted.");
        Reporter.log(new ExcelUtility().getDataByTestId("CampaignData", id, "Description"), true);
    }

    @Test(groups = {"BVA", "Functional"})
    public void verifyLowerBoundaryClosingDate() throws Exception {
        String id = "CM_008";
        createCampaign(id);
        Assert.assertTrue(driver.getCurrentUrl().contains("DetailView"),
                "Closing-date boundary value was not accepted.");
        Reporter.log(new ExcelUtility().getDataByTestId("CampaignData", id, "Description"), true);
    }
}

package Campaign;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.CampaignPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CampaignSystemTest extends BaseClass {

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

    @Test(groups = {"System", "Acceptance"})
    public void verifyEndToEndCampaignCreation() throws Exception {
        String id = "CM_015";
        createCampaign(id);
        Assert.assertTrue(driver.getCurrentUrl().contains("DetailView"),
                "End-to-end Campaign creation failed.");
        Reporter.log(new ExcelUtility().getDataByTestId("CampaignData", id, "Description"), true);
        Reporter.log("End-to-end Campaign creation verified.", true);
    }
}

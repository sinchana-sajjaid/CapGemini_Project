package Campaign;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.CampaignPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CampaignFunctionalTest extends BaseClass {

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

    @Test(groups = {"Functional", "EP"})
    public void verifyCampaignCreationWithValidDetails() throws Exception {
        String id = "CM_002";
        createCampaign(id);
        Assert.assertTrue(driver.getCurrentUrl().contains("DetailView"),
                "Campaign was not created successfully.");
        Reporter.log(new ExcelUtility().getDataByTestId("CampaignData", id, "Description"), true);
    }

    @Test(groups = {"Functional", "EP"})
    public void verifyCampaignWithoutStatus() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String id = "CM_005";
        CampaignPage page = new CampaignPage(driver);

        page.clickCampaigns();
        page.clickCreateCampaign();
        page.enterCampaignName(excel.getDataByTestId("CampaignData", id, "CampaignName"));
        page.selectAssignedTo(excel.getDataByTestId("CampaignData", id, "AssignedTo"));
        page.selectCampaignType(excel.getDataByTestId("CampaignData", id, "Type"));
        page.enterClosingDate(excel.getDataByTestId("CampaignData", id, "ClosingDate"));
        page.clickSave();

        Assert.assertTrue(driver.getCurrentUrl().contains("DetailView"),
                "Campaign without Status was not submitted successfully.");
        Reporter.log(excel.getDataByTestId("CampaignData", id, "Description"), true);
    }
}

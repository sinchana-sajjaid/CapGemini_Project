package Campaign;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.CampaignPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CampaignSmokeTest extends BaseClass {

    @Test(groups = {"Smoke"})
    public void verifyCampaignPageNavigation() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String id = "CM_001";

        CampaignPage page = new CampaignPage(driver);
        page.clickCampaigns();
        page.clickCreateCampaign();

        Assert.assertTrue(driver.getCurrentUrl().contains("EditView"),
                "Campaign Create page is not displayed.");
        Assert.assertTrue(page.isCampaignNameFieldDisplayed(),
                "Campaign Name field is not displayed.");

        Reporter.log(excel.getDataByTestId("CampaignData", id, "Description"), true);
    }
}

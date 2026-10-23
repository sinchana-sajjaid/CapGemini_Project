package Campaign;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.CampaignPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CampaignNegativeTest extends BaseClass {

    private String getValidationMessage() {
        try {
            Alert alert = driver.switchTo().alert();
            String message = alert.getText();
            alert.accept();
            return message;
        } catch (NoAlertPresentException e) {
            return driver.findElement(By.tagName("body")).getText();
        }
    }

    private void verifyMessage(String id, String expected) {
        String actual = getValidationMessage();
        Assert.assertNotNull(expected, id + ": Expected message is missing in Excel.");
        Assert.assertFalse(expected.trim().isEmpty(), id + ": Expected message is blank in Excel.");
        Assert.assertTrue(actual.toLowerCase().contains(expected.toLowerCase()),
                id + ": Expected [" + expected + "] but actual message was [" + actual + "]");
        Reporter.log(id + " validation: " + actual, true);
    }

    @Test(groups = {"Negative", "EP"})
    public void verifyCampaignWithoutName() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String id = "CM_003";
        String expected = excel.getDataByTestId("CampaignData", id, "ExpectedMessage");
        CampaignPage page = new CampaignPage(driver);

        page.clickCampaigns();
        page.clickCreateCampaign();
        page.selectAssignedTo(excel.getDataByTestId("CampaignData", id, "AssignedTo"));
        page.selectCampaignStatus(excel.getDataByTestId("CampaignData", id, "Status"));
        page.selectCampaignType(excel.getDataByTestId("CampaignData", id, "Type"));
        page.enterClosingDate(excel.getDataByTestId("CampaignData", id, "ClosingDate"));
        page.clickSave();

        verifyMessage(id, expected);
    }
}

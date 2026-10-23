package leads;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.HomePage;
import com.vtiger.objRepo.LeadsPage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LeadsSmokeTest extends BaseClass {

    @Test(groups = {"Smoke"})
    public void verifyLeadsCreatePageOpens() throws Exception {

        ExcelUtility excel = new ExcelUtility();

        new HomePage(driver).clickLeads();

        LeadsPage page = new LeadsPage(driver);
        page.clickCreateLead();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("EditView"),
                "Create Lead page did not open."
        );

        Assert.assertTrue(
                driver.findElement(By.tagName("body"))
                        .getText().contains("Last Name"),
                "Create Lead form is not displayed correctly."
        );

        Reporter.log(
                excel.getDataByTestId(
                        "LeadsData", "LD_001", "Description"),
                true
        );
    }
}


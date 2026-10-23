package leads;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.HomePage;
import com.vtiger.objRepo.LeadsPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LeadsFunctionalTest extends BaseClass {
    private void createLead(String id) throws Exception {
        ExcelUtility excel = new ExcelUtility();
        new HomePage(driver).clickLeads();
        LeadsPage page = new LeadsPage(driver);
        page.clickCreateLead();
        page.enterFirstName(excel.getDataByTestId("LeadsData", id, "FirstName"));
        page.enterLastName(excel.getDataByTestId("LeadsData", id, "LastName"));
        page.enterCompany(excel.getDataByTestId("LeadsData", id, "Company"));
        page.enterPhone(excel.getDataByTestId("LeadsData", id, "Phone"));
        page.enterEmail(excel.getDataByTestId("LeadsData", id, "Email"));
        page.selectAssignedTo(excel.getDataByTestId("LeadsData", id, "AssignedTo"));
        page.enterCity(excel.getDataByTestId("LeadsData", id, "City"));
        page.enterState(excel.getDataByTestId("LeadsData", id, "State"));
        page.enterPostalCode(excel.getDataByTestId("LeadsData", id, "PostalCode"));
        page.enterCountry(excel.getDataByTestId("LeadsData", id, "Country"));
        page.clickSave();
    }

    @Test(groups = {"Functional", "Positive"})
    public void verifyValidLeadCreation() throws Exception {
        createLead("LD_002");
        Assert.assertTrue(driver.getCurrentUrl().contains("DetailView"),
                "Valid lead was not created.");
        Reporter.log("LD_002: Valid lead created.", true);
    }

    @Test(groups = {"Functional", "Positive"})
    public void verifyLeadWithoutPhone() throws Exception {
        createLead("LD_006");
        Assert.assertTrue(driver.getCurrentUrl().contains("DetailView"),
                "Lead without optional Phone was not accepted.");
        Reporter.log("LD_006: Lead without Phone accepted.", true);
    }

    @Test(groups = {"Functional", "Positive"})
    public void verifyLeadWithoutEmail() throws Exception {
        createLead("LD_007");
        Assert.assertTrue(driver.getCurrentUrl().contains("DetailView"),
                "Lead without optional Email was not accepted.");
        Reporter.log("LD_007: Lead without Email accepted.", true);
    }
}

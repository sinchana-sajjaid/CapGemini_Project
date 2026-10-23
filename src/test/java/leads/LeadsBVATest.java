package leads;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.HomePage;
import com.vtiger.objRepo.LeadsPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LeadsBVATest extends BaseClass {

    private void submit(String id) throws Exception {

        ExcelUtility excel = new ExcelUtility();

        new HomePage(driver).clickLeads();

        LeadsPage page = new LeadsPage(driver);
        page.clickCreateLead();

        page.enterFirstName(
                excel.getDataByTestId("LeadsData", id, "FirstName"));
        page.enterLastName(
                excel.getDataByTestId("LeadsData", id, "LastName"));
        page.enterCompany(
                excel.getDataByTestId("LeadsData", id, "Company"));
        page.enterPhone(
                excel.getDataByTestId("LeadsData", id, "Phone"));
        page.enterEmail(
                excel.getDataByTestId("LeadsData", id, "Email"));
        page.selectAssignedTo(
                excel.getDataByTestId("LeadsData", id, "AssignedTo"));
        page.enterCity(
                excel.getDataByTestId("LeadsData", id, "City"));
        page.enterState(
                excel.getDataByTestId("LeadsData", id, "State"));
        page.enterPostalCode(
                excel.getDataByTestId("LeadsData", id, "PostalCode"));
        page.enterCountry(
                excel.getDataByTestId("LeadsData", id, "Country"));

        page.clickSave();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("DetailView"),
                id + ": Lead was not saved successfully."
        );

        String pageText = driver.findElement(
                org.openqa.selenium.By.tagName("body")
        ).getText().toLowerCase();

        Assert.assertFalse(
                pageText.contains("fatal error")
                        || pageText.contains("internal server error"),
                id + ": Application error appeared after saving the lead."
        );

        Reporter.log(
                id + ": " +
                excel.getDataByTestId("LeadsData", id, "Description"),
                true
        );
    }

    @Test(groups = {"BVA", "Functional"})
    public void verifyPostalCodeBoundary() throws Exception {
        submit("LD_011");
    }

    @Test(groups = {"BVA", "Functional"})
    public void verifyMinimumFirstNameBoundary() throws Exception {
        submit("LD_012");
    }

    @Test(groups = {"BVA", "Functional"})
    public void verifyMinimumLastNameBoundary() throws Exception {
        submit("LD_013");
    }
}

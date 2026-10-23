package leads;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.HomePage;
import com.vtiger.objRepo.LeadsPage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LeadsSystemTest extends BaseClass {

    @Test(groups = {"System", "Acceptance"})
    public void verifySecondUniqueLeadCreation() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String id = "LD_015";

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
                id + ": Lead was not created successfully."
        );

        String bodyText = driver.findElement(
                By.tagName("body")
        ).getText().toLowerCase();

        Assert.assertFalse(
                bodyText.contains("fatal error")
                        || bodyText.contains("internal server error"),
                id + ": Application error appeared after saving."
        );

        Reporter.log(
                id + ": " + excel.getDataByTestId(
                        "LeadsData", id, "Description"),
                true
        );
    }
}


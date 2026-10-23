package leads;

import com.vtiger.baseClass.BaseClass;
import com.vtiger.genericUtility.ExcelUtility;
import com.vtiger.objRepo.HomePage;
import com.vtiger.objRepo.LeadsPage;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LeadsNegativeTest extends BaseClass {

    private void submitLead(String id) throws Exception {

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

        String assignedTo =
                excel.getDataByTestId("LeadsData", id, "AssignedTo");

        if (assignedTo == null || assignedTo.trim().isEmpty()) {
            page.clearAssignedTo();
        } else {
            page.selectAssignedTo(assignedTo);
        }

        page.enterCity(
                excel.getDataByTestId("LeadsData", id, "City"));
        page.enterState(
                excel.getDataByTestId("LeadsData", id, "State"));
        page.enterPostalCode(
                excel.getDataByTestId("LeadsData", id, "PostalCode"));
        page.enterCountry(
                excel.getDataByTestId("LeadsData", id, "Country"));

        page.clickSave();
    }

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

    private void verifyExpectedMessage(
            String id, String fieldName) throws Exception {

        ExcelUtility excel = new ExcelUtility();

        String actual = getValidationMessage();
        String expected = excel.getDataByTestId(
                "LeadsData", id, "ExpectedMessage");

        Assert.assertNotNull(expected,
                id + ": Expected validation message is missing in Excel.");

        Assert.assertFalse(expected.trim().isEmpty(),
                id + ": Expected validation message is blank in Excel.");

        Assert.assertTrue(
                actual.toLowerCase().contains(expected.toLowerCase()),
                id + ": Expected " + fieldName
                        + " validation. Actual message: " + actual);

        Reporter.log(id + ": Validation displayed: " + actual, true);
    }

    @Test(groups = {"Negative", "EP"})
    public void verifyLeadWithoutLastName() throws Exception {
        submitLead("LD_004");
        verifyExpectedMessage("LD_004", "Last Name");
    }

    @Test(groups = {"Negative", "EP"})
    public void verifyLeadWithoutCompany() throws Exception {
        submitLead("LD_005");
        verifyExpectedMessage("LD_005", "Company");
    }

    @Test(groups = {"Negative", "EP"})
    public void verifyInvalidPhoneFormat() throws Exception {
        submitLead("LD_009");

        String actual = getValidationMessage().toLowerCase();

        boolean rejected = actual.contains("phone")
                || actual.contains("invalid")
                || actual.contains("must be")
                || !driver.getCurrentUrl().contains("DetailView");

        Assert.assertTrue(
                rejected,
                "Invalid phone was not rejected or retained for correction."
        );

        Reporter.log("LD_009: Invalid phone response: " + actual, true);
    }

    @Test(groups = {"Negative", "EP"})
    public void verifyInvalidEmailFormat() throws Exception {
        submitLead("LD_010");

        String actual = getValidationMessage().toLowerCase();

        boolean rejected = actual.contains("email")
                || actual.contains("invalid")
                || actual.contains("must be")
                || !driver.getCurrentUrl().contains("DetailView");

        Assert.assertTrue(
                rejected,
                "Invalid email was not rejected or retained for correction."
        );

        Reporter.log("LD_010: Invalid email response: " + actual, true);
    }
}

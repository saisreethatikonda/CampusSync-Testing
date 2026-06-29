package com.cognizant.testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cognizant.elementrepository.CompanyDirectoryPage;

public class TS004CompanyDirectoryTest extends CampusSyncTestBase {

    @Test(priority = 1, groups = {"TS-004", "CompanyDirectory", "Smoke", "Positive", "Functional", "Regression"})
    public void TC006_Add_Company_Directory_ID() {
        CompanyDirectoryPage companyDirectoryPage = loginAsSuperAdminAndOpenCompanyDirectory();

        String employeeId = testDataReader.getValue("valid.employee.id");
        companyDirectoryPage.addId("Employee", employeeId);
        Assert.assertTrue(
                companyDirectoryPage.waitUntilRecordIsDisplayed(employeeId)
                        || companyDirectoryPage.isSuccessMessageDisplayed(),
                "Employee ID " + employeeId + " should be added successfully to Company Directory.");

        String candidateId = testDataReader.getValue("valid.candidate.id");
        companyDirectoryPage.addId("Candidate", candidateId);
        Assert.assertTrue(
                companyDirectoryPage.waitUntilRecordIsDisplayed(candidateId)
                        || companyDirectoryPage.isSuccessMessageDisplayed(),
                "Candidate ID " + candidateId + " should be added successfully to Company Directory.");
    }

    @Test(priority = 2, groups = {"TS-004", "CompanyDirectory", "Negative", "Functional", "Regression"})
    public void TC007_Invalid_ID_Validation() {
        CompanyDirectoryPage companyDirectoryPage = loginAsSuperAdminAndOpenCompanyDirectory();

        companyDirectoryPage.selectIdType("Employee");
        companyDirectoryPage.enterId(testDataReader.getValue("invalid.employee.id"));
        companyDirectoryPage.clickAdd();
        Assert.assertTrue(companyDirectoryPage.isValidationMessageDisplayed(),
                "Format validation error message should be displayed for invalid ID.");

        companyDirectoryPage.clearId();
        companyDirectoryPage.enterId(testDataReader.getValue("special.character.id"));
        companyDirectoryPage.clickAdd();
        Assert.assertTrue(companyDirectoryPage.isValidationMessageDisplayed(),
                "Format validation error message should be displayed for special character ID.");

        companyDirectoryPage.clearId();
        companyDirectoryPage.clickAdd();
        Assert.assertTrue(companyDirectoryPage.isValidationMessageDisplayed(),
                "Mandatory field validation error message should be displayed for blank ID.");
    }

    @Test(priority = 3, groups = {"TS-004", "CompanyDirectory", "Positive", "Negative", "Functional", "Regression"})
    public void TC008_Boundary_Value_ID_Validation() {
        CompanyDirectoryPage companyDirectoryPage = loginAsSuperAdminAndOpenCompanyDirectory();

        assertIdRejected(
                companyDirectoryPage,
                "Employee",
                testDataReader.getValue("underlength.employee.id"),
                "Underlength Employee ID should be rejected.");
        assertIdRejected(
                companyDirectoryPage,
                "Candidate",
                testDataReader.getValue("underlength.candidate.id"),
                "Underlength Candidate ID should be rejected.");

        assertIdAccepted(
                companyDirectoryPage,
                "Employee",
                testDataReader.getValue("lower.bound.employee.id"),
                "Lower-bound valid Employee ID should be accepted and added successfully.");
        assertIdAccepted(
                companyDirectoryPage,
                "Candidate",
                testDataReader.getValue("lower.bound.candidate.id"),
                "Lower-bound valid Candidate ID should be accepted and added successfully.");

        assertIdAccepted(
                companyDirectoryPage,
                "Employee",
                testDataReader.getValue("upper.bound.employee.id"),
                "Upper-bound valid Employee ID should be accepted.");
        assertIdAccepted(
                companyDirectoryPage,
                "Candidate",
                testDataReader.getValue("upper.bound.candidate.id"),
                "Upper-bound valid Candidate ID should be accepted.");

        assertIdRejected(
                companyDirectoryPage,
                "Employee",
                testDataReader.getValue("overlength.employee.id"),
                "Overlength Employee ID should be rejected.");
        assertIdRejected(
                companyDirectoryPage,
                "Candidate",
                testDataReader.getValue("overlength.candidate.id"),
                "Overlength Candidate ID should be rejected.");
    }

    private void assertIdRejected(CompanyDirectoryPage companyDirectoryPage, String idType, String directoryId,
            String assertionMessage) {
        companyDirectoryPage.selectIdType(idType);
        companyDirectoryPage.clearId();
        companyDirectoryPage.enterId(directoryId);
        companyDirectoryPage.clickAdd();

        Assert.assertTrue(companyDirectoryPage.isValidationMessageDisplayed(), assertionMessage);
    }

    private void assertIdAccepted(CompanyDirectoryPage companyDirectoryPage, String idType, String directoryId,
            String assertionMessage) {
        companyDirectoryPage.selectIdType(idType);
        companyDirectoryPage.clearId();
        companyDirectoryPage.enterId(directoryId);
        companyDirectoryPage.clickAdd();

        Assert.assertTrue(
                companyDirectoryPage.waitUntilRecordIsDisplayed(directoryId)
                        || companyDirectoryPage.isSuccessMessageDisplayed(),
                assertionMessage);
    }

}

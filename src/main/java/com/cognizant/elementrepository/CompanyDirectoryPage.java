package com.cognizant.elementrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.cognizant.base.BasePage;

public class CompanyDirectoryPage extends BasePage {

    private final By companyidentifier = By.id("admin-stats");
    private final By idTypeDropdown = By.id("admin-new-type");
    private final By idInput = By.id("admin-new-id");
    private final By addButton = By.id("admin-add-btn");
    private final By successMessage = By.id("admin-message");
    private final By validationMessage = By.id("admin-form-error");    

    public CompanyDirectoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCompanyDirectoryPageDisplayed() {
        return isElementDisplayed(companyidentifier)
                || isElementDisplayed(idTypeDropdown)
                || getCurrentUrl().toLowerCase().contains("directory");
    }

    public void selectIdType(String idType) {
        actionUtil.selectByValue(idTypeDropdown, toAdminIdTypeValue(idType));
    }

    public void enterId(String id) {
        actionUtil.type(idInput, id);
    }

    public void clearId() {
        actionUtil.clear(idInput);
    }

    public void clickAdd() {
        actionUtil.click(addButton);
    }

    public void addId(String idType, String id) {
        selectIdType(idType);
        enterId(id);
        clickAdd();
    }

    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successMessage);
    }

    public boolean isValidationMessageDisplayed() {
        return isElementDisplayed(validationMessage);
    }

    public String getValidationMessage() {
        return actionUtil.getText(validationMessage);
    }

    public boolean isRecordDisplayed(String id) {
        return !driver.findElements(recordById(id)).isEmpty();
    }

    public boolean waitUntilRecordIsDisplayed(String id) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(recordById(id)));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public void applyEmployeeFilter() {
        actionUtil.selectByValue(By.id("admin-filter"), "EMPLOYEE");
    }

    public void applyCandidateFilter() {
        actionUtil.selectByValue(By.id("admin-filter"), "CANDIDATE");
    }
    
    private By recordById(String id) {
        return By.xpath("//tr[.//*[normalize-space()='" + id + "'] or .//td[normalize-space()='"
                + id + "']]");
    }
 
    private String toAdminIdTypeValue(String idType) {
        return "Candidate".equalsIgnoreCase(idType) ? "CANDIDATE" : "EMPLOYEE";
    }
}

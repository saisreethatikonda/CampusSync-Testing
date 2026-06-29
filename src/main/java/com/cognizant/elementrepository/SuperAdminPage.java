package com.cognizant.elementrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cognizant.base.BasePage;

public class SuperAdminPage extends BasePage {

    private final By superAdminIdentifier = By.id("admin-stats");
    private final By companyDirectoryLink = By.id("nav-admin-link");

    public SuperAdminPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSuperAdminPageDisplayed() {
        return isElementDisplayed(superAdminIdentifier) || getCurrentUrl().toLowerCase().contains("admin");
    }

    public CompanyDirectoryPage openCompanyDirectoryPage() {
        if (isElementDisplayed(companyDirectoryLink)) {
            actionUtil.click(companyDirectoryLink);
        }
        return new CompanyDirectoryPage(driver);
    }
}

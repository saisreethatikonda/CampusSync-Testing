package com.cognizant.testscripts;

import org.testng.Assert;

import com.cognizant.base.BaseTest;
import com.cognizant.elementrepository.CompanyDirectoryPage;
import com.cognizant.elementrepository.DashboardPage;
import com.cognizant.elementrepository.HomePage;
import com.cognizant.elementrepository.LoginPage;
import com.cognizant.elementrepository.SuperAdminPage;
import com.cognizant.elementrepository.WishlistPage;

public class CampusSyncTestBase extends BaseTest {

    protected CompanyDirectoryPage loginAsSuperAdminAndOpenCompanyDirectory() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(), "HomePage should be displayed by default.");

        LoginPage loginPage = homePage.openLoginPage();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed.");

        loginPage.login(
                testDataReader.getValue("superAdmin.username"),
                testDataReader.getValue("superAdmin.password"));

        SuperAdminPage superAdminPage = new SuperAdminPage(driver);
        Assert.assertTrue(superAdminPage.isSuperAdminPageDisplayed(),
                "Super Admin should be logged in successfully.");

        CompanyDirectoryPage companyDirectoryPage = superAdminPage.openCompanyDirectoryPage();
        Assert.assertTrue(companyDirectoryPage.isCompanyDirectoryPageDisplayed(),
                "Company Directory page should be displayed.");

        return companyDirectoryPage;
    }

    protected WishlistPage loginAsUserAndOpenWishlist() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(), "HomePage should be displayed by default.");

        LoginPage loginPage = homePage.openLoginPage();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed.");

        loginPage.login(
                testDataReader.getValue("user.username"),
                testDataReader.getValue("user.password"));

        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(),
                "User should be logged in successfully and redirected to Dashboard page.");

        WishlistPage wishlistPage = dashboardPage.openWishlistPage();
        Assert.assertTrue(wishlistPage.isWishlistPageDisplayed(), "Wishlist page should be displayed.");

        return wishlistPage;
    }
}

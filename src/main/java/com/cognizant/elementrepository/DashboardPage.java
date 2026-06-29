package com.cognizant.elementrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cognizant.base.BasePage;

public class DashboardPage extends BasePage {

    private final By dashboardIdentifier = By.id("dashboard-greeting");
    private final By wishlistLink = By.id("nav-wishlist-link");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardDisplayed() {
        return isElementDisplayed(dashboardIdentifier) || getCurrentUrl().toLowerCase().contains("dashboard");
    }

    public WishlistPage openWishlistPage() {
        actionUtil.click(wishlistLink);
        return new WishlistPage(driver);
    }
}

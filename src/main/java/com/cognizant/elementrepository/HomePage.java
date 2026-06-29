package com.cognizant.elementrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cognizant.base.BasePage;

public class HomePage extends BasePage {

    private final By homePageIdentifier = By.cssSelector(".landing-nav");
    private final By loginLink = By.id("landing-signin-btn");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isHomePageLoaded() {
        return isElementDisplayed(homePageIdentifier) || (getPageTitle() != null && !getPageTitle().trim().isEmpty());
    }

    public String getHomePageTitle() {
        return getPageTitle();
    }

    public LoginPage openLoginPage() {
        if (isElementDisplayed(loginLink)) {
            actionUtil.click(loginLink);
        } else {
            driver.navigate().to(driver.getCurrentUrl().replaceAll("/$", "") + "/login");
        }
        return new LoginPage(driver);
    }
}

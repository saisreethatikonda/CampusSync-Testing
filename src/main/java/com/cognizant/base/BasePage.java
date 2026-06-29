package com.cognizant.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cognizant.util.ActionUtil;
import com.cognizant.util.FrameworkConstants;
import com.cognizant.util.JavaScriptUtil;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ActionUtil actionUtil;
    protected JavaScriptUtil javaScriptUtil;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT));
        this.actionUtil = new ActionUtil(driver);
        this.javaScriptUtil = new JavaScriptUtil(driver);
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected String getPageTitle() {
        return driver.getTitle();
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected boolean isElementDisplayed(By locator) {
        return actionUtil.isDisplayed(locator);
    }
}

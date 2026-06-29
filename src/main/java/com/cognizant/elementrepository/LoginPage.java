package com.cognizant.elementrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cognizant.base.BasePage;

public class LoginPage extends BasePage {

    private final By usernameInput = By.id("login-email");
    private final By passwordInput = By.id("login-password");
    private final By loginButton = By.id("login-submit-btn");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(usernameInput) && isElementDisplayed(passwordInput);
    }

    public void login(String username, String password) {
        actionUtil.type(usernameInput, username);
        actionUtil.type(passwordInput, password);
        actionUtil.click(loginButton);
    }
}

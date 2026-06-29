package com.cognizant.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

    private WebDriver driver;

    public JavaScriptUtil(WebDriver driver) {
        this.driver = driver;
    }

    public Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    public void clickUsingJavaScript(WebElement element) {
        executeScript("arguments[0].click();", element);
    }

    public void scrollToElement(WebElement element) {
        executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    public void scrollToBottom() {
        executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void highlightElement(WebElement element) {
        String originalStyle = element.getAttribute("style");
        executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                "border: 2px solid red; background: yellow;");
        GenericUtil.sleep(300);
        executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                originalStyle == null ? "" : originalStyle);
    }
}

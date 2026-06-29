package com.cognizant.elementrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cognizant.base.BasePage;

public class ListingDetailPage extends BasePage {

    private final By detailPageIdentifier = By.id("detail-container");
    private final By pgNameText = By.id("detail-pg-name");
    private final By locationText = By.cssSelector("div[class='chips'] span[class='badge']");
    private final By rentText = By.id("detail-rent");
    private final By sharingTypeText = By.cssSelector("#detail-info .chips .badge.amber");
    private final By vacancyDetailsText = By.id("detail-vacancy");
    private final By providerContactText = By.id("detail-contact");
    
    public ListingDetailPage(WebDriver driver) {
        super(driver);
    }

    public boolean isListingDetailPageDisplayed() {
        return isElementDisplayed(detailPageIdentifier) || getCurrentUrl().toLowerCase().contains("listing");
    }

    public boolean areListingDetailsDisplayed() {
        return isElementDisplayed(pgNameText)
                && isElementDisplayed(locationText)
                && isElementDisplayed(rentText)
                && isElementDisplayed(sharingTypeText)
                && isElementDisplayed(vacancyDetailsText)
                && isElementDisplayed(providerContactText);
    }
}

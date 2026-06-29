package com.cognizant.elementrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cognizant.base.BasePage;

public class WishlistPage extends BasePage {

    private final By wishlistIdentifier = By.id("wishlist-grid");
    private final By listingCards = By.cssSelector("[id^='wishlist-card-']");
    private final By listingImage = By.cssSelector(".photo");
    private final By bedVacancyBadge = By.cssSelector(".vac-pill");
    private final By filledHeartIcon = By.cssSelector("[id^='wishlist-remove-btn-']");
    private final By pgNameText = By.cssSelector("[id^='wishlist-name-']");
    private final By locationText = By.cssSelector(".loc");
    private final By priceText = By.cssSelector(".rent");
    private final By availabilityBadge = By.cssSelector(".badge");
    private final By viewDetailsButton = By.cssSelector("[id^='wishlist-view-btn-']");

    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    public boolean isWishlistPageDisplayed() {
        return isElementDisplayed(wishlistIdentifier) || getCurrentUrl().toLowerCase().contains("wishlist");
    }

    public boolean hasSavedListing() {
        return !driver.findElements(listingCards).isEmpty();
    }

    public boolean areFirstListingCardComponentsDisplayed() {
        WebElement card = getFirstListingCard();

        return hasVisibleChild(card, listingImage)
                && hasVisibleChild(card, bedVacancyBadge)
                && hasVisibleChild(card, filledHeartIcon)
                && hasVisibleChild(card, pgNameText)
                && hasVisibleChild(card, locationText)
                && hasVisibleChild(card, priceText)
                && hasVisibleChild(card, availabilityBadge)
                && hasVisibleChild(card, viewDetailsButton);
    }

    public String getFirstListingName() {
        return getFirstListingCard().findElement(pgNameText).getText().trim();
    }

    public void removeFirstListingFromWishlist() {
        getFirstListingCard().findElement(filledHeartIcon).click();
    }

    public ListingDetailPage openFirstListingDetails() {
        getFirstListingCard().findElement(viewDetailsButton).click();
        return new ListingDetailPage(driver);
    }

    public boolean isListingDisplayed(String listingName) {
        try {
            By dynamicListing = By.xpath("//*[contains(@id, 'wishlist-card-') and contains(., '" + listingName + "')]");
            return driver.findElement(dynamicListing).isDisplayed();
        } catch (NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e) {
            return false;
        }
    }

    public boolean waitUntilListingIsRemoved(final String listingName) {
        try {
            wait.until(driver -> !isListingDisplayed(listingName));
            return !isListingDisplayed(listingName);
        } catch (Exception exception) {
            return false;
        }
    }

    private WebElement getFirstListingCard() {
        return waitForVisibility(listingCards);
    }

    private boolean hasVisibleChild(WebElement parent, By childLocator) {
        try {
            return parent.findElement(childLocator).isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }
}

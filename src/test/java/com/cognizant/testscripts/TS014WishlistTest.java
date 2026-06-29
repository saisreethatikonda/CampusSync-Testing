package com.cognizant.testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cognizant.elementrepository.ListingDetailPage;
import com.cognizant.elementrepository.WishlistPage;

public class TS014WishlistTest extends CampusSyncTestBase {

    @Test(priority = 1, groups = {"TS-014", "Wishlist", "Positive", "Functional", "Regression"})
    public void TC027_Wishlist_Detail_View() {
        WishlistPage wishlistPage = loginAsUserAndOpenWishlist();

        Assert.assertTrue(wishlistPage.hasSavedListing(),
                "At least one saved listing card should be displayed on Wishlist page.");

        ListingDetailPage listingDetailPage = wishlistPage.openFirstListingDetails();
        Assert.assertTrue(listingDetailPage.isListingDetailPageDisplayed(),
                "User should be redirected to the Listing Detail page.");
        Assert.assertTrue(listingDetailPage.areListingDetailsDisplayed(),
                "PG name, location, rent, sharing type, vacancy details, and provider contact details should be displayed.");
    }

    @Test(priority = 2, groups = {"TS-014", "Wishlist", "Positive", "Functional", "Regression"})
    public void TC026_Remove_Wishlist_Listing() {
        WishlistPage wishlistPage = loginAsUserAndOpenWishlist();

        Assert.assertTrue(wishlistPage.hasSavedListing(),
                "At least one saved listing card should be displayed on Wishlist page.");

        String listingName = wishlistPage.getFirstListingName();
        wishlistPage.removeFirstListingFromWishlist();

        Assert.assertTrue(wishlistPage.waitUntilListingIsRemoved(listingName),
                "Removed listing should not be displayed on Wishlist page.");
    }
}

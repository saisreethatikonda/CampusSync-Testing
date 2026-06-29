package com.cognizant.testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cognizant.elementrepository.WishlistPage;

public class TS013WishlistCardLayoutTest extends CampusSyncTestBase {

    @Test(priority = 1, groups = {"TS-013", "Wishlist", "UI", "Regression"})
    public void TC025_Wishlist_Card_Layout() {
        WishlistPage wishlistPage = loginAsUserAndOpenWishlist();

        Assert.assertTrue(wishlistPage.hasSavedListing(),
                "At least one saved listing card should be displayed on Wishlist page.");
        Assert.assertTrue(wishlistPage.areFirstListingCardComponentsDisplayed(),
                "Listing card should display image, vacancy badge, heart icon, PG name, location, price, availability badge, and View Details button.");
    }
}

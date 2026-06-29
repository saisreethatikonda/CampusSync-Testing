# HybridFramework

This starter project follows the Eclipse folder structure from your screenshot and includes Selenium TestNG automation classes for these CampusSync test cases:

- `TC006_Add_Company_Directory_ID`
- `TC007_Invalid_ID_Validation`
- `TC008_Boundary_Value_ID_Validation`
- `TC025_Wishlist_Card_Layout`
- `TC026_Remove_Wishlist_Listing`
- `TC027_Wishlist_Detail_View`

## Eclipse Import

1. Open Eclipse.
2. Select `File > Import > Maven > Existing Maven Projects`.
3. Browse to this folder: `HybridFramework`.
4. Select the project and click `Finish`.
5. Right-click `testng.xml` and run it as a TestNG suite.

## Main Packages

- `com.cognizant.base`: common page and test setup classes.
- `com.cognizant.elementrepository`: page object classes.
- `com.cognizant.util`: reusable framework utilities.
- `com.cognizant.testscripts`: TestNG test classes.

## Config

Update browser and URL values here:

`src/test/resources/ConfigProperty/config.properties`

Update usernames, passwords, and test IDs here:

`src/test/resources/TestData/campussync-testdata.properties`

## Locator Status

The page objects use locators found from the deployed CampusSync app:

- Login page: `login-email`, `login-password`, `login-submit-btn`
- Admin page: `admin-new-type`, `admin-new-id`, `admin-add-btn`, `admin-filter`, `admin-directory-table`
- Dashboard/nav: `dashboard-greeting`, `nav-wishlist-link`, `nav-admin-link`
- Wishlist page: `wishlist-grid`, `wishlist-card-*`, `wishlist-remove-btn-*`, `wishlist-view-btn-*`
- Listing detail page: `detail-container`, `detail-pg-name`, `detail-rent`, `detail-vacancy`, `detail-contact`

If the deployed app changes, update the locators in `src/main/java/com/cognizant/elementrepository`.

## Default Config

```properties
browser=chrome
url=https://sdet-final-project-one.vercel.app
headless=false
implicitWait=5
explicitWait=10
```

## Test Data

```properties
superAdmin.username=superadmin@example.com
superAdmin.password=Password@123
user.username=user@example.com
user.password=Password@123
valid.employee.id=CTS1006
valid.candidate.id=CAND2004
invalid.employee.id=ABC1001
special.character.id=CTS@123
delete.employee.id=CTS9009
underlength.employee.id=CTS12
lower.bound.employee.id=CTS123
underlength.candidate.id=CAND12
lower.bound.candidate.id=CAND123
upper.bound.employee.id=CTS1938502847501938472610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958
upper.bound.candidate.id=CAND938502847501938472610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958
overlength.employee.id=CTS19385028475019384726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583
overlength.candidate.id=CAND9385028475019384726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583726104958372610495837261049583
```

## Run

Right-click `testng.xml` and select `Run As > TestNG Suite`.

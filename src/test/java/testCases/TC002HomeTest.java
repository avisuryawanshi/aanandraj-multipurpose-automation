package testCases;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
/**
 * TC002HomeTest validates login functionality and home page UI element visibility
 * and performs tab navigation tests after successful login.
 * ✅ Test Group: Sanity
 * ✅ Prerequisite: App must launch successfully and login must be functional
 */

public class TC002HomeTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(TC002HomeTest.class);

    private LoginPage loginPage;
    private HomePage homePage;

    // Initializes page objects before running any test in this class.
    @BeforeClass
    public void setUpPages() {
        loginPage = new LoginPage(mobileDriver);
        homePage = new HomePage(mobileDriver);
        log.info("[INIT] Page objects initialized for LoginPage and HomePage.");
    }

    /**
     * ✅ Test Case TC001: Login to the application with specific test credentials.
     * Pre-condition: App must be launched and on the login screen.
     * Steps:
     *  1. Enter username
     *  2. Enter password
     *  3. Click login button
     *  4. Handle fingerprint prompt (select No)
     */
    @Test(priority = 0, groups= {"sanity"})
    void testLoginWithSpecificCredentials() {
        log.info("[TEST] Logging in with username 'test' and password '123456'");

        log.info("Step 1: Entering username");
        loginPage.enterUser("test");

        log.info("Step 2: Entering password");
        loginPage.enterPassword("123456");

        log.info("Step 3: Clicking login button");
        loginPage.clickLogin();

        log.info("Step 4: Handling fingerprint prompt (if shown/appears)");
        loginPage.handleFingerprintPromptNo();

        // log.info("Step 4: Accept fingerprint prompt (if shown/appears)");
        // loginPage.handleFingerprintPromptYes();

        log.info("[PASS] Login test completed successfully.");
    }

    /**
     * ✅ TC002: Verify Home Page UI Elements
     * Depends on: testLoginWithSpecificCredentials()
     * Verifies:
     *  - App Title
     *  - Booking SVG
     *  - Searching SVG
     *  - Overflow Menu
     *  - Upcoming / Draft / Completed Events Tabs
     *  - Expense and Events Bottom Tabs
     */
    @Test(priority = 1, dependsOnMethods = {"testLoginWithSpecificCredentials"}, groups= {"sanity"})
    void testTitleTabsVisible() {
        log.info("[TEST] Verifying key UI elements on HomePage...");

        log.info("🔍 Checking: App title is displayed");
        Assert.assertTrue(homePage.isTitleDisplayed(), "❌ App title is not displayed");

        log.info("🔍 Checking: Booking Tab SVG is displayed");
        Assert.assertTrue(homePage.isBookingDisplayed(), "❌ Booking Tab SVG is not displayed");

        log.info("🔍 Checking: Searching SVG is displayed");
        Assert.assertTrue(homePage.isSearchingDisplayed(), "❌ Searching SVG is not displayed");

        log.info("🔍 Checking: Overflow menu icon (3-dot) is visible");
        Assert.assertTrue(homePage.isOverflowMenuDisplayed(), "❌ Overflow menu SVG is not displayed");

        log.info("🔍 Checking: Upcoming Events tab is visible");
        Assert.assertTrue(homePage.isUpcomingEventsTabVisible(), "❌ Upcoming Events tab is not visible");

        log.info("🔍 Checking: Draft Events tab is visible");
        Assert.assertTrue(homePage.isDraftEventsTabVisible(), "❌ Draft Events Tab is not visible");

        log.info("🔍 Checking: Completed Events tab is visible");
        Assert.assertTrue(homePage.isCompleteEventsTabVisible(), "❌ Complete Events Tab is not visible");

        // Optional: Uncomment if applicable
        // log.info("🔍 Checking: Refresh button is visible");
        // Assert.assertTrue(homePage.isRefreshBtnVisible(), "❌ Refresh button is not visible");

        log.info("🔍 Checking: Expense bottom tab is visible");
        Assert.assertTrue(homePage.isExpenseTabVisible(), "❌ Expense tab is not visible");

        log.info("🔍 Checking: Events bottom tab is visible");
        Assert.assertTrue(homePage.isEventsTabVisible(), "❌ Events tab is not visible");

        log.info("[PASS] All critical UI elements on HomePage verified successfully.");
    }

    /**
     * ✅ TC003: Navigates between all available bottom and top tabs.
     * Depends on: Login and HomePage visibility
     * Actions:
     *  - Tap Draft Events tab
     *  - Tap Upcoming Events tab
     *  - Tap Completed Events tab
     *  - Tap Bottom Tabs: Expense and Events
     */
    @Test(priority = 2, dependsOnMethods = {"testLoginWithSpecificCredentials", "testTitleTabsVisible"}, groups= {"sanity"})
    void testTabsNavigation(){
        log.info("[TEST] Navigating through all tabs on the HomePage...");

        log.info("Tap → Draft Events Tab");
        homePage.tapDraftEventsTab();

        log.info("Tap → Upcoming Events Tab");
        homePage.tapUpcomingEventsTab();

        log.info("Tap → Completed Events Tab");
        homePage.tapCompletedEventsTab();

        log.info("Tap → Expense Bottom Tab");
        homePage.tapExpenseTab();

        log.info("Tap → Events Bottom Tab");
        homePage.tapEventsTab();

        log.info("[PASS] Tab navigation completed successfully.");
    }



}

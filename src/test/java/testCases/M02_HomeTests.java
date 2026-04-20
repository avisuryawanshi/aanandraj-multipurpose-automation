package testCases;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
/**
 * TC002HomeTest validates login functionality and home page UI element visibility
 * and performs tab navigation tests after successful login.
 * ✅ Test Group: Sanity
 * ✅ Prerequisite: App must launch successfully and login must be functional
 */

public class M02_HomeTests extends BaseTest {

    private static final Logger log = LogManager.getLogger(M02_HomeTests.class);

    private LoginPage loginPage;
    private HomePage homePage;

    //------------------------------------------------------------------------------------------------------------------
    // 🛠️ Test Setup
    //------------------------------------------------------------------------------------------------------------------
    // Initializes page objects before running any test in this class.
    @BeforeMethod(alwaysRun = true)
    public void setUpPages() {
        loginPage = new LoginPage(mobileDriver);
        homePage = new HomePage(mobileDriver);
        log.info("[INIT] Page objects initialized for LoginPage and HomePage.");
    }

    //------------------------------------------------------------------------------------------------------------------
    // ✅ TC001 - Home Page UI Elements Verification
    /** Verifies:
     *  - App Title
     *  - Booking SVG
     *  - Searching SVG
     *  - Overflow Menu
     *  - Upcoming / Draft / Completed Events Tabs
     *  - Expense and Events Bottom Tabs
     *  - Navigating through all tabs on the HomePage...*/
    //------------------------------------------------------------------------------------------------------------------
    @Test(priority = 1, description = "TC001 - Verifying key UI elements on HomePage", groups= {"sanity"})
    void testTitleTabsVisible() {

        log.info("[TEST] Logging in with username 'test' and password '123456'");

        loginPage.enterUser("test");
        loginPage.enterPassword("123456");
        loginPage.clickLogin();
        loginPage.handleFingerprintPromptNo();

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
        log.info("🔍 Checking: Refresh button is visible");
        Assert.assertTrue(homePage.isRefreshBtnVisible(), "❌ Refresh button is not visible");

        log.info("🔍 Checking: Expense bottom tab is visible");
        Assert.assertTrue(homePage.isExpenseTabVisible(), "❌ Expense tab is not visible");

        log.info("🔍 Checking: Events bottom tab is visible");
        Assert.assertTrue(homePage.isEventsTabVisible(), "❌ Events tab is not visible");

        log.info("[PASS] All critical UI elements on HomePage verified successfully.");

    // -----------------------------------------------------------------------------------------------------------------

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

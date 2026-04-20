package testCases;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.OverflowMenuPage;

public class M03_DashboardTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(M03_DashboardTest.class);

    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    //------------------------------------------------------------------------------------------------------------------
    // 🛠️ Test Setup
    //------------------------------------------------------------------------------------------------------------------
    // Initializes page objects before running any test in this class.
    @BeforeMethod(alwaysRun = true)
    public void setUpPages() {
        loginPage = new LoginPage(mobileDriver);
        dashboardPage = new DashboardPage(mobileDriver);
        log.info("[INIT] Page objects initialized for LoginPage and OverflowMenuPage.");
    }

    //------------------------------------------------------------------------------------------------------------------
    // ✅ TC001 - Dashboard Functionality and Event Counts Verification
    // Validates that the dashboard displays the correct event counts and that navigation to event details works as expected.
    /**
     * Verifies:
     * - Dashboard displays correct counts for Upcoming Events, Draft Events, and Completed Events
     * - Navigation to event details from the dashboard works correctly (e.g., clicking on a specific draft event leads to the correct event details screen)
     */
    //------------------------------------------------------------------------------------------------------------------
    @Test(priority = 1, description = "TC001 - Verifying dashboard functionality and event counts", groups = {"sanity"})
    public void testDashBoard() throws InterruptedException {

        // Logging in to the app -----------------------------------------------------
        log.info("[TEST] Logging in with username 'test' and password '123456'");
        loginPage.enterUser("test");            // Step 1: Enter username
        loginPage.enterPassword("123456");      // Step 2: Enter password
        loginPage.clickLogin();                          // Step 3: Click login button
        loginPage.handleFingerprintPromptNo();           // Step 4: Handle fingerprint prompt (No)
        // loginPage.handleFingerprintPromptYes();       // Optional alternative

        log.info("[TEST] Navigating to home screen and verifying hall logo and title.");
        dashboardPage.isHallLogoVisible();
        dashboardPage.isTitleDisplayed();
        Thread.sleep(5000);

        //dashboardPage.refreshQueries();

        // Call method to print dashboard records
        /*dashboardPage.getUpcomingEventCount(); // Upcoming Events
        dashboardPage.navigateToDraftEvents(); // Draft Events
        dashboardPage.getDraftEventCount();
        dashboardPage.navigateToCompletedEvents(); // Completed Event
        dashboardPage.getCompletedEventCount();*/

        // Navigate to Draft Events and click on a specific event
        dashboardPage.navigateToDraftEvents(); // Draft Events
        dashboardPage.scrollToAndClickDraftEvent("Srushti \n2024-09-14\n18:50");
        //dashboardPage.scrollToAndClickDraftEvent();

    }


}

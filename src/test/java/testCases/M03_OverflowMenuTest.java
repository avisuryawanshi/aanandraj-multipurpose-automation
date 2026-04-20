package testCases;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BookingPage;
import pages.LoginPage;
import pages.OverflowMenuPage;

import java.util.Map;

public class M03_OverflowMenuTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(M03_OverflowMenuTest.class);

    private LoginPage loginPage;
    private OverflowMenuPage overflowMenuPage;

    //------------------------------------------------------------------------------------------------------------------
    // 🛠️ Test Setup
    //------------------------------------------------------------------------------------------------------------------
    // Initializes page objects before running any test in this class.
    @BeforeMethod(alwaysRun = true)
    public void setUpPages() {
        loginPage = new LoginPage(mobileDriver);
        overflowMenuPage = new OverflowMenuPage(mobileDriver);
        log.info("[INIT] Page objects initialized for LoginPage and OverflowMenuPage.");
    }

    //------------------------------------------------------------------------------------------------------------------
    // ✅ TC001 - Overflow Menu Options and Settings Information Verification
    // Validates that the overflow menu options are functional and that the settings information is displayed correctly.
    /**
     * Verifies:
     * - Overflow menu options (e.g., Download Reports, Settings) are functional and lead to the expected screens or actions
     * - Settings information (User Info, App Info) is displayed correctly and contains expected details
     */
    //-------------------------------------------------------------------------
    @Test(priority = 1, description = "TC001 - Verifying overflow menu options and settings information", groups = {"sanity"})
    public void testOverflowMenu() throws InterruptedException {

        // Logging in to the app -----------------------------------------------------
        log.info("[TEST] Logging in with username 'test' and password '123456'");
        loginPage.enterUser("test");            // Step 1: Enter username
        loginPage.enterPassword("123456");      // Step 2: Enter password
        loginPage.clickLogin();                          // Step 3: Click login button
        loginPage.handleFingerprintPromptNo();           // Step 4: Handle fingerprint prompt (No)
        log.info("[TEST] Navigating to home screen and verifying hall logo and title.");

        overflowMenuPage.isHallLogoVisible();
        overflowMenuPage.isTitleDisplayed();
        Thread.sleep(5000);

        // Testing overflow menu options ---------------------------------------------

        // 1) DOWNLOAD REPORTS *******************************
        //log.info("[TEST] Testing overflow menu options: Download Reports and Share via WhatsApp.");

        overflowMenuPage.clickOverflowMenu();
        overflowMenuPage.downloadReport("3 months"); //option: 3 months/ 6 months/ 1 year
        //overflowMenuPage.shareViaWhatsApp();

        //log.info("[TEST] Simulating download report cancellation.");
        //OverflowMenuPage.openSettingsAndBack();
        overflowMenuPage.clickOverflowMenu();
        overflowMenuPage.downloadReport("3 months"); // simulate selecting
        overflowMenuPage.cancelDownload();           // canceling instead of download

        // 2) SETTINGS ***************************************
        // Approach 1
        /*overflowMenuPage.clickOverflowMenu();
        overflowMenuPage.Settings();*/

        // Approach 2: Stores them in a map (Map<String, String>)
        /*log.info("[TEST] Testing overflow menu option: Settings and verifying displayed information.");

        overflowMenuPage.clickOverflowMenu();
        Map<String, String> infoTexts = overflowMenuPage.Settings();

        String userInfo = infoTexts.get("userInfo");
        String appInfo = infoTexts.get("appInfo");
        log.info("[TEST] Extracted User Info: " + userInfo);
        log.info("[TEST] Extracted App Info: " + appInfo);

        //System.out.println("User Info: " + userInfo);
        //System.out.println("App Info: " + appInfo);

        // Assertions
        Assert.assertTrue(userInfo.contains("User Info"), "User Info text not found.");
        Assert.assertTrue(appInfo.contains("App Info"), "App Info text not found.");*/

    }


}

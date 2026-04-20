package testCases;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchPage;

public class M03_SearchingTests extends BaseTest {

    private static final Logger log = LogManager.getLogger(M03_SearchingTests.class);

    private LoginPage loginPage;
    private SearchPage searchPage;

    //------------------------------------------------------------------------------------------------------------------
    // 🛠️ Test Setup
    //------------------------------------------------------------------------------------------------------------------
    // Initializes page objects before running any test in this class.
    @BeforeMethod(alwaysRun = true)
    public void setUpPages() {
        loginPage = new LoginPage(mobileDriver);
        searchPage = new SearchPage(mobileDriver);
        log.info("[INIT] Page objects initialized for LoginPage and HomePage.");
    }

    //------------------------------------------------------------------------------------------------------------------
    // ✅ TC001 - Search Functionality with Valid Input
    //------------------------------------------------------------------------------------------------------------------
    @Test(priority = 1, description = "TC001 - Verify search functionality with valid input", groups = {"Sanity"})
    public void testSearchFunction() throws InterruptedException {

        // Log in to the app before performing search tests

        log.info("[TEST] Logging in with username 'test' and password '123456'");
        loginPage.enterUser("test");
        loginPage.enterPassword("123456");
        loginPage.clickLogin();
        loginPage.handleFingerprintPromptNo();
        log.info("[TEST] Successfully logged in, proceeding with search functionality tests...");

    // -----------------------------------------------------------------------------------------------------------------
        // Verify key UI elements on HomePage before proceeding with search tests
        searchPage.isHallLogoVisible();
        searchPage.isTitleDisplayed();
        Thread.sleep(5000);

        log.info("[TEST] Navigating to Search tab and performing search operations...");
        searchPage.clickSearchTab();                   //– taps the search button
        log.info("[TEST] Entering search text 'John' and fetching results...");
        searchPage.enterSearchText("John");      //– enters text and hides the keyboard

        log.info("[TEST] Fetching visible booking info from search results...");
        //searchPage.getSearchResultText();            //– fetches the visible booking info
        String result = searchPage.getSearchResultText();

        log.info("[TEST] Search result text: " + result);
        searchPage.clickBookingDetails();              //– opens booking detail section
        searchPage.clickBackToSearchPage();
        searchPage.clickBackToHomePage();              //– handles navigation
        log.info("[TEST] Search functionality test completed successfully.");

    }
}

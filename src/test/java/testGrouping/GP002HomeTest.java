package testGrouping;

import base.MultiDeviceBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class GP002HomeTest extends MultiDeviceBaseTest {

    /*private LoginPage loginPage;
    private HomePage homePage;

    @BeforeClass(groups = {"Sanity", "Master"})
    public void setUpPages() {
        loginPage = new LoginPage(mobileDriver);
        homePage = new HomePage(mobileDriver);
    }

    @Test(priority = 0, groups= {"sanity","Master"})
    void testLoginWithSpecificCredentials() {
        loginPage.enterUser("test");            // Step 1: Enter username
        loginPage.enterPassword("123456");      // Step 2: Enter password
        loginPage.clickLogin();                          // Step 3: Click login button
        loginPage.handleFingerprintPromptNo();           // Step 4: Handle fingerprint prompt (No)
        // loginPage.handleFingerprintPromptYes();       // Optional alternative
    }

    @Test(priority = 1, dependsOnMethods = {"testLoginWithSpecificCredentials"}, groups= {"sanity", "Master"})
    void testTitleTabsVisible() {
        Assert.assertTrue(homePage.isTitleDisplayed(), "App title is not displayed");
        Assert.assertTrue(homePage.isBookingDisplayed(), "Booking Tab SVG is not displayed");
        Assert.assertTrue(homePage.isSearchingDisplayed(), "Searching SVG is not displayed");
        Assert.assertTrue(homePage.isOverflowMenuDisplayed(), " top-right corner of the Overflow menu SVG is not displayed");
        Assert.assertTrue(homePage.isUpcomingEventsTabVisible(), "Upcoming Events tab is not visible");
        Assert.assertTrue(homePage.isDraftEventsTabVisible(), "Draft Events Tab is not Visible");
        Assert.assertTrue(homePage.isCompleteEventsTabVisible(), "Complete Events Tab is not Visible");
        //Assert.assertTrue(homePage.isRefreshBtnVisible(), "Refresh button is not visible"); // Optionally: Verify loader or timestamp change
        Assert.assertTrue(homePage.isExpenseTabVisible(), "Expense tab is not visible");
        Assert.assertTrue(homePage.isEventsTabVisible(), "Events tab is not visible");
    }

    @Test(priority = 2, dependsOnMethods = {"testLoginWithSpecificCredentials", "testTitleTabsVisible"}, groups= {"sanity", "Master"})
    void testTabsNavigation(){
        homePage.tapDraftEventsTab();
        homePage.tapUpcomingEventsTab();
        homePage.tapCompletedEventsTab();
        homePage.tapExpenseTab();
        homePage.tapEventsTab();
    }*/



}

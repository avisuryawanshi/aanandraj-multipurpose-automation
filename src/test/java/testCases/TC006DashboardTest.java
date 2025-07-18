package testCases;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.OverflowMenuPage;

import java.util.List;

public class TC006DashboardTest extends BaseTest {

    @Test(priority = 0)
    public void testLoginWithSpecificCredentials() {

        LoginPage loginPage = new LoginPage(mobileDriver);

        loginPage.enterUser("test");            // Step 1: Enter username
        loginPage.enterPassword("123456");      // Step 2: Enter password
        loginPage.clickLogin();                          // Step 3: Click login button
        loginPage.handleFingerprintPromptNo();           // Step 4: Handle fingerprint prompt (No)
        // loginPage.handleFingerprintPromptYes();       // Optional alternative
    }

    @Test(priority = 1, dependsOnMethods = {"testLoginWithSpecificCredentials"})
    public void testDashBoard() throws InterruptedException {

        DashboardPage dashboardPage = new DashboardPage(mobileDriver);

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

        // scroll specific element on Draft event
        dashboardPage.navigateToDraftEvents(); // Draft Events
        dashboardPage.scrollToAndClickDraftEvent("Srushti \n2024-09-14\n18:50");
        //dashboardPage.scrollToAndClickDraftEvent();

    }


}

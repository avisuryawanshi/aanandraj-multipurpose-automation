package testGrouping;

import base.MultiDeviceBaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SearchPage;

public class GP004SearchingTest extends MultiDeviceBaseTest {

    /*@Test(priority = 0)
    public void testLoginWithSpecificCredentials() {

        LoginPage loginPage = new LoginPage(mobileDriver);

        loginPage.enterUser("test");            // Step 1: Enter username
        loginPage.enterPassword("123456");      // Step 2: Enter password
        loginPage.clickLogin();                          // Step 3: Click login button
        loginPage.handleFingerprintPromptNo();           // Step 4: Handle fingerprint prompt (No)
        // loginPage.handleFingerprintPromptYes();       // Optional alternative
    }

    @Test(priority = 1, dependsOnMethods = {"testLoginWithSpecificCredentials"})
    public void testSearchFunction() throws InterruptedException {

        SearchPage searchPage = new SearchPage(mobileDriver);

        searchPage.isHallLogoVisible();
        searchPage.isTitleDisplayed();
        Thread.sleep(5000);

        searchPage.clickSearchTab();               //– taps the search button
        searchPage.enterSearchText("John"); //– enters text and hides the keyboard
        //searchPage.getSearchResultText();         //– fetches the visible booking info
        String result = searchPage.getSearchResultText();
        searchPage.clickBookingDetails();         //– opens booking detail section
        searchPage.clickBackToSearchPage();
        searchPage.clickBackToHomePage();         //– handles navigation

    }*/
}

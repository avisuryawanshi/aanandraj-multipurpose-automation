package testGrouping;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.OverflowMenuPage;

import java.util.Map;

public class TC005OverflowMenuTest extends BaseTest {

    @Test(priority = 0, groups= {"sanity","Master"})
    public void testLoginWithSpecificCredentials() {

        LoginPage loginPage = new LoginPage(mobileDriver);

        loginPage.enterUser("test");            // Step 1: Enter username
        loginPage.enterPassword("123456");      // Step 2: Enter password
        loginPage.clickLogin();                          // Step 3: Click login button
        loginPage.handleFingerprintPromptNo();           // Step 4: Handle fingerprint prompt (No)
        // loginPage.handleFingerprintPromptYes();       // Optional alternative
    }

    @Test(priority = 1, dependsOnMethods = {"testLoginWithSpecificCredentials"}, groups= {"sanity","Master"})
    public void testOverflowMenu() throws InterruptedException {

        OverflowMenuPage overflowMenuPage = new OverflowMenuPage(mobileDriver);

        overflowMenuPage.isHallLogoVisible();
        overflowMenuPage.isTitleDisplayed();
        Thread.sleep(5000);

        // 1) DOWNLOAD REPORTS *******************************
        /*overflowMenuPage.clickOverflowMenu();
        overflowMenuPage.downloadReport("3 months"); //option: 3 months/ 6 months/ 1 year
        overflowMenuPage.shareViaWhatsApp();*/

        //OverflowMenuPage.openSettingsAndBack();
        //overflowMenuPage.downloadReport("3 months"); // simulate selecting
        //overflowMenuPage.cancelDownload();           // canceling instead of download

        // 2) SETTINGS ***************************************
        // Approach 1
        /*overflowMenuPage.clickOverflowMenu();
        overflowMenuPage.Settings();*/

        // Approach 2: Stores them in a map (Map<String, String>)
        overflowMenuPage.clickOverflowMenu();
        Map<String, String> infoTexts = overflowMenuPage.Settings();

        String userInfo = infoTexts.get("userInfo");
        String appInfo = infoTexts.get("appInfo");

        System.out.println("User Info: " + userInfo);
        System.out.println("App Info: " + appInfo);

        // Assertions
        Assert.assertTrue(userInfo.contains("User Info"), "User Info text not found.");
        Assert.assertTrue(appInfo.contains("App Info"), "App Info text not found.");

    }


}

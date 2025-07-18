package testGrouping;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;

public class TC001LoginTest extends BaseTest {


// ---- Generate specific username and passcode ------------------------------------------------------------------------
    @Test(groups = {"Sanity", "Master"})
    public void testLoginWithSpecificCredentials() throws InterruptedException {

        LoginPage loginPage = new LoginPage(mobileDriver);

// Positive Login Flow =================================================================================================

        loginPage.enterUser("test");        // Step 1: Enter username
        loginPage.enterPassword("123456");   // Step 2: Enter password
        loginPage.clickLogin();                         // Step 3: Click login button
        loginPage.handleFingerprintPromptNo();          // Step 4: Handle fingerprint prompt if appears
        //loginPage.handleFingerprintPromptYes();

// Login PageUI Test ===================================================================================================

        /*System.out.println("Validating app title...");
        Assert.assertTrue(loginPage.isAppTitleDisplayed(), "App title is not visible");

        System.out.println("Validating heading...");
        Assert.assertTrue(loginPage.isHeadingDisplayed(), "Login heading is not visible");

        System.out.println("Validating username field...");
        Assert.assertTrue(loginPage.isUserFieldVisible(), "Username field is not visible");

        System.out.println("Validating password field...");
        Assert.assertTrue(loginPage.isPasswordFieldVisible(), "Password field is not visible");

        System.out.println("Validating login button...");
        Assert.assertTrue(loginPage.isLoginButtonVisible(), "Login button is not visible");*/

// Negative Login Scenarios ============================================================================================

        /*loginPage.enterUser("");         // Entering blank username
        loginPage.enterPassword("");     // Entering blank password
        loginPage.clickLogin();                     // Clicking login

        Assert.assertTrue(loginPage.isValidationAlertDisplayed(), "Validation alert not displayed for invalid login");*/

// Field-Level Testing =================================================================================================

        //System.out.println("===== Field-Level Validation Testing Started =====");

        // Scenario 1: Empty username and password
        /*loginPage.enterUser("");
        loginPage.enterPassword("");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.getValidationAlertDisplayed(), "Alert not shown for empty fields");*/

        // Scenario 2: Special characters in username
        /*loginPage.enterUser("$%&@!*");
        loginPage.enterPassword("1234");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isValidationAlertDisplayed(), "Alert not shown for special character input");*/

        // Scenario 3: Valid username, short password
        /*loginPage.enterUser("tester");
        loginPage.enterPassword("12"); // Too short
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isValidationAlertDisplayed(), "Alert not shown for short password");*/

        // Scenario 4: Password only, no username
        /*loginPage.enterUser("");
        loginPage.enterPassword("123");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isValidationAlertDisplayed(), "Alert not shown when username is empty");*/

        // Scenario 5: Username only, no password
        /*loginPage.enterUser("tester");
        loginPage.enterPassword("");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isValidationAlertDisplayed(), "Alert not shown when password is empty");*/

        // Scenario 6: Excessively long username and password
        /*loginPage.enterUser("thisisaverylongusernameexceedinglimit");
        loginPage.enterPassword("123456");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isValidationAlertDisplayed(), "Alert not shown for overly long inputs");*/




    }

}

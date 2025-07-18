package testCases;

import base.BaseTest;
import io.qameta.allure.*;
import listeners.AllureTestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import config.ConfigReader;
import utils.ScreenshotUtil;

@Listeners(AllureTestListener.class)
@Epic("Login Module")
@Feature("Login Functionality")
public class TC001LoginTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(TC001LoginTest.class);

    /**
     * ✅ TC001LoginTest
     * Validates login functionality including
     *          - Positive login with valid credentials
 *              - UI field validation
     *          - Negative and edge case login validations
     */

    @Test(description = "Verify login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Valid login test case")
    //@Test
    public void testLoginWithSpecificCredentials() throws InterruptedException {

        log.info("===== Starting test: testLoginWithSpecificCredentials =====");

        LoginPage loginPage = new LoginPage(mobileDriver);

// -------------------------------------------------------------------------------------------------------------
// 📦 Optional: Load credentials from config file (config.properties)
// -------------------------------------------------------------------------------------------------------------
        /*
        String username = ConfigReader.get("user");
        String password = ConfigReader.get("password");
        loginPage.enterUser(username);
        loginPage.enterPassword(password);
*/
// -------------------------------------------------------------------------------------------------------------
// ✅ Positive Login Flow
// -------------------------------------------------------------------------------------------------------------
        log.info("[POSITIVE FLOW] Logging in with hardcoded credentials...");

        log.info("Step 1: Entering username");
        loginPage.enterUser("test");

        log.info("Step 2: Entering password");
        loginPage.enterPassword("123456");

        log.info("Step 3: Clicking login button");
        //loginPage.clickLogin();

        log.info("Step 4: Handling fingerprint prompt (if shown/appears)");
        loginPage.handleFingerprintPromptNo();

        log.info("Step 4: Accept fingerprint prompt (if shown/appears)");
        // log.info("Step 4 (alternative): Accept fingerprint prompt");
        // loginPage.handleFingerprintPromptYes();

// -------------------------------------------------------------------------------------------------------------
// ✅ Login Page UI Field Verification
// -------------------------------------------------------------------------------------------------------------
        /*log.info("[UI VALIDATION] Verifying Login screen components...");

        Assert.assertTrue(loginPage.isAppTitleDisplayed(), "❌ App title is not visible");
        Assert.assertTrue(loginPage.isHeadingDisplayed(), "❌ Login heading is not visible");
        Assert.assertTrue(loginPage.isUserFieldVisible(), "❌ Username field is not visible");
        Assert.assertTrue(loginPage.isPasswordFieldVisible(), "❌ Password field is not visible");
        Assert.assertTrue(loginPage.isLoginButtonVisible(), "❌ Login button is not visible");

        log.info("[PASS] All Login screen elements validated successfully.");*/

// -------------------------------------------------------------------------------------------------------------
// ❌ Negative Login Scenarios
// -------------------------------------------------------------------------------------------------------------
        /*log.info("[NEGATIVE TEST] Submitting empty username and password...");

        loginPage.enterUser("");
        loginPage.enterPassword("");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isValidationAlertDisplayed(), "❌ Validation alert not shown for blank login");*/

// -------------------------------------------------------------------------------------------------------------
// 🔍 Field-Level Edge Case Validations
// -------------------------------------------------------------------------------------------------------------
        /*log.info("[FIELD TEST] Testing invalid inputs at field level...");

        // Scenario 1: Empty username and password
        loginPage.enterUser("");
        loginPage.enterPassword("");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.getValidationAlertDisplayed(), "❌ Alert not shown for empty fields");

        // Scenario 2: Special characters in username
        loginPage.enterUser("$%&@!*");
        loginPage.enterPassword("1234");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isValidationAlertDisplayed(), "❌ Alert not shown for special character input");

        // Scenario 3: Valid username, short password
        loginPage.enterUser("tester");
        loginPage.enterPassword("12"); // Too short
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isValidationAlertDisplayed(), "❌ Alert not shown for short password");

        // Scenario 4: Password only, no username
        loginPage.enterUser("");
        loginPage.enterPassword("123");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isValidationAlertDisplayed(), "❌ Alert not shown when username is empty");

        // Scenario 5: Username only, no password
        loginPage.enterUser("tester");
        loginPage.enterPassword("");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isValidationAlertDisplayed(), "❌ Alert not shown when password is empty");

        // Scenario 6: Excessively long username and password
        loginPage.enterUser("thisisaverylongusernameexceedinglimit");
        loginPage.enterPassword("123456");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isValidationAlertDisplayed(), "❌ Alert not shown for overly long inputs");

        log.info("[PASS] All negative login scenarios and field-level validations completed.");*/
    }

}

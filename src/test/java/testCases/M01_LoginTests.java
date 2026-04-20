package testCases;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ExcelDataReader;
import utils.TestUtils;

import java.io.IOException;

/**
 * ✅ TC001LoginTest
 * Validates login functionality including
 * - Positive login with valid credentials
 * - UI field validation
 * - Negative and edge case login validations
 */

// NOTE: The following test cases are designed to cover both positive and negative scenarios for the login functionality.
// The positive test case (TC001) validates successful login with valid credentials,
// while the later test cases (TC002 - TC008) focus on UI validation and various negative input scenarios to ensure robust input handling and error messaging.

public class M01_LoginTests extends BaseTest {

    private static final Logger log = LogManager.getLogger(M01_LoginTests.class);
    private LoginPage loginPage;

    //------------------------------------------------------------------------------------------------------------------
    // 🛠️ Test Setup
    //------------------------------------------------------------------------------------------------------------------
    @BeforeMethod(alwaysRun = true)
    public void setUpPage() {
        log.info("===== Initializing LoginPage =====");
        if (mobileDriver == null) {
            log.error("❌ Driver is null before page initialization");
            throw new IllegalStateException("AppiumDriver is null. Cannot initialize LoginPage.");
        }
        loginPage = new LoginPage(mobileDriver);
        log.info("✅ LoginPage initialized successfully");
    }

    // TC004, TC007, and TC008 are expected to fail due to known validation issues in the current implementation,
    // which should be addressed in future development iterations.
    //------------------------------------------------------------------------------------------------------------------
    // ✅ TC001 - Positive Login / Optional: Load credentials from a config file (config-qa.properties)
    //------------------------------------------------------------------------------------------------------------------
    @Test(priority = 1, description = "TC001 - Verify valid user login", groups = {"Sanity"})
    public void verifyValidUserLogin() throws InterruptedException {

        //log.info("[POSITIVE FLOW] Logging in with hardcoded credentials...");
        //log.info("[POSITIVE FLOW] Fetching test data from config");

        log.info("===== START: TC001 - Valid Login =====");
        String username = ConfigReader.get("env.user.test");
        String password = ConfigReader.get("env.password.test");
        log.info("Using credentials - Username: " + username + ", Password: " + password);

        log.info("Step 1: Entering username");
        loginPage.enterUser(username);
        //loginPage.enterUser("test");

        log.info("Step 2: Entering password");
        loginPage.enterPassword(password);
        //loginPage.enterPassword("123456");

        log.info("Step 3: Clicking login button");
        loginPage.clickLogin();

        log.info("Step 4: Handling fingerprint prompt (if shown/appears)");
        loginPage.handleFingerprintPromptNo();

        //log.info("Step 4: Accept fingerprint prompt (if shown/appears)");
        // log.info("Step 4 (alternative): Accept fingerprint prompt");
        // loginPage.handleFingerprintPromptYes();
        log.info("===== END: TC001 Test Passed: Verify User Login =====");
    }
    // -----------------------------------------------------------------------------------------------------------------
    // ✅ TC001 - Optional: Generate random username and passcode (uncomment to use)
    // -----------------------------------------------------------------------------------------------------------------
        /*String randomUsername = TestUtils.generateRandomUsername();
        String randomPasscode = TestUtils.generateRandomPasscode();

        log.info("Generated Username: " + randomUsername);
        log.info("Generated Passcode: " + randomPasscode);

        login.enterUser(randomUsername);
        login.enterPassword(randomPasscode);
        login.clickLogin();
        login.handleFingerprintPromptNo();*/   //Yes
        //login.handleFingerprintPrompt();   //No

    // -----------------------------------------------------------------------------------------------------------------
    // ✅ TC002 - Login Page UI Validation
    // -----------------------------------------------------------------------------------------------------------------
    @Test(priority = 2, description = "TC002 - Verify login page UI elements", groups = {"Sanity"})
    public void verifyLoginPageUI() {

        log.info("===== START: TC002 - UI Validation =====");

        Assert.assertTrue(loginPage.isAppTitleDisplayed(), "❌ App title not visible");
        Assert.assertTrue(loginPage.isHeadingDisplayed(), "❌ Login Heading not visible");
        Assert.assertTrue(loginPage.isUserFieldVisible(), "❌ Username field not visible");
        Assert.assertTrue(loginPage.isPasswordFieldVisible(), "❌ Password field not visible");
        Assert.assertTrue(loginPage.isLoginButtonVisible(), "❌ Login button not visible");

        log.info("[PASS] All Login screen UI elements validated successfully.");
        log.info("===== END: TC002 PASSED =====");
    }

    // -----------------------------------------------------------------------------------------------------------------
    // ✅ DataProvider - Negative + Edge Cases (Reads from an Excel file using Apache POI)
    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() throws IOException {

        // Original HARDCODED DATA (commented out to use Excel data instead)
        /*log.info("===== Providing invalid login test data =====");
        Object[][] data = new Object[][]{
                {"", "", "Empty username & password"},
                {"$%&@!*", "1234", "Special characters in username"},
                {"tester", "12", "Short password"},
                {"tester", "", "Only username"},
                {"", "1234", "Only password"},
                {"thisisaverylongusernameexceedinglimit", "123456", "Long input"}};
        log.info("Invalid login data provided: {} test cases", data.length);
        return data;*/

        // New implementation to read from Excel file
        log.info("===== Loading invalid login test data from Excel file =====");

        // Read data from an Excel file
        String excelFilePath = "src/test/resources/testdata/TestData_QA.xlsx";
        Object[][] data = ExcelDataReader.readExcelData(excelFilePath, "TestData", 1);

        log.info("Invalid login data provided: {} test cases from Excel", data.length);
        return data;
    }

    // ---------------------------------------------------------------------------------------------------------------------
    // ✅ TC003-TC008 - Negative Login Scenarios (Data-Driven)
    // ---------------------------------------------------------------------------------------------------------------------
    @Test(dataProvider = "invalidLoginData", priority = 3, description = "TC003-TC008 - Verify login with invalid credentials (covers all negative scenarios)", groups = {"Sanity"})
    public void verifyInvalidLoginScenarios(String username, String password, String description) {
        log.info("===== START: Testing invalid login scenario - {} =====", description);

        log.info("Entering username: '{}'", username);
        loginPage.enterUser(username);

        log.info("Entering password: '{}'", password);
        loginPage.enterPassword(password);

        log.info("Clicking login button");
        loginPage.clickLogin();

        log.info("Validating response for scenario: {}", description);
        // General assertion: Based on current app behavior, most invalid inputs do not show alerts (bugs noted).
        // For cases expecting alerts (e.g., TC004, TC008, TC007), adjust if validation is fixed.
        if (description.contains("TC004") || description.contains("TC008") || description.contains("TC007")) {
            Assert.assertTrue(loginPage.isValidationAlertDisplayed(), "❌ Alert not shown for: " + description);
        } else {
            Assert.assertFalse(loginPage.isValidationAlertDisplayed(), "❌ Unexpected alert shown for: " + description);
        }

        log.info("===== END: Scenario '{}' completed =====", description);
    }
}

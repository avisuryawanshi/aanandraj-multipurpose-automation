package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import listeners.ExtentReportManager;
import utils.ConfigReader;
import utils.ScreenshotUtil;

import java.lang.reflect.Method;

/**
 * 📦 BaseTest - (Core base class) Abstract base class for all test classes
 * Handles driver setup, ExtentReports, logging, load properties files, screenshots, video recording, and TestNG lifecycle.
 */

public class BaseTest {

//  ░░░░░ Global Test Objects ░░░░░ ====================================================================================

    // Thread-safe driver instance for parallel execution
    // private static final ThreadLocal<AppiumDriver> driverInstance = new ThreadLocal<>();
    // private static final ThreadLocal<WebDriverWait> waitInstance = new ThreadLocal<>();

    // Log4j and Extent Objects - Manages entire report config
    /**At the top, you've got these global objects:*/

    protected static ExtentReports extent;                                       // Responsible for common info in the report
    protected static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();   // Responsible for updating each test result
    protected static final ThreadLocal<String> TL_DEVICE = new ThreadLocal<>();
    protected static final Logger log = LogManager.getLogger(BaseTest.class);

    protected AppiumDriver mobileDriver;    // AppiumDriver variable for mobile testing

//  ░░░░░ TestNG Hooks - SUITE LEVEL ░░░░░ =============================================================================

    //  Before Suite - Initialize ExtentReport once per test suite
    @BeforeSuite(alwaysRun = true)
    public void startReport() {

        // ✅ Load config here (only once)
        ConfigReader.loadProperties();
        extent = ExtentReportManager.getInstance();
    }

    //  Flush ExtentReport after suite ends /exactly once per suite
    @AfterSuite(alwaysRun = true)
    public void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

//  ░░░░░ TestNG Hooks - CLASS LEVEL ░░░░░ =============================================================================

    // Before Class - Setup before each test class (ExtentReports only)
    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        // ExtentReports setup is handled in @BeforeSuite
        // Driver setup moved to @BeforeMethod for method-level sessions
    }

    // AfterClass 🔻 Cleanup after test class (ExtentReports only)
    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        // Driver cleanup moved to @AfterMethod for method-level sessions
        // Only cleanup class-level thread data
        TL_DEVICE.remove();
    }

// ░░░░░ TestNG Hooks - METHOD LEVEL ░░░░░ =============================================================================

    // Before Method - Launch driver and create Extent test node for each method
    @BeforeMethod(alwaysRun = true)
    @Parameters({"platformName", "deviceName", "udid", "platformVersion", "automationName"})
    public void setUpMethod(@Optional String platformName,
                            @Optional String deviceName,
                            @Optional String udid,
                            @Optional String platformVersion,
                            @Optional String automationName,
                           Method method) {

        // ✅ Step 1: Launch NEW driver for this test method
        log.info("🚀 Launching Appium driver for test method: " + method.getName());
        DriverController.launchAppiumDriverWithExistingServer(platformName, deviceName, udid, platformVersion, automationName);
        mobileDriver = DriverController.getDriver();

        if (deviceName != null) TL_DEVICE.set(deviceName);

        // ✅ Step 2: Create Extent test node
        ExtentTest test = extent.createTest(method.getName());
        extentTest.set(test);

        if (TL_DEVICE.get() != null) {
            extentTest.get().assignCategory(TL_DEVICE.get());
        }

        log.info("✅ Driver launched and Extent test node created for: " + method.getName());
    }

    /* ░░░░░ Screenshot if the test case has failed & Extent logging ░░░░░ */
    @AfterMethod(alwaysRun = true)
    public void updateExtentAndQuit(ITestResult result) {
        ExtentTest test = extentTest.get();
        String methodName = result.getName();

        log.info("====================================================");
        log.info(" STARTING TEST: " + methodName);
        log.info("====================================================");

        String tag = TL_DEVICE.get();
        String nameWithDevice = (tag != null ? tag + "_" : "") + methodName;

        switch (result.getStatus()) {
            case ITestResult.FAILURE -> {
                String screenshotPath = ScreenshotUtil.captureScreenshot(mobileDriver, nameWithDevice); // 🛠️ Capture screenshot and get the PATH
                test.fail(result.getThrowable()).addScreenCaptureFromPath(screenshotPath);              // 🛠️ Attach to Extent Report
                log.error("❌ FAILED: " + methodName + " | Device: " + tag);
            }
            case ITestResult.SUCCESS -> {
                test.pass("✅ Test passed");
                log.info("✅ PASSED: "+ methodName + " | Device: " + tag);
            }
            case ITestResult.SKIP -> {
                test.skip("⚠️ Test skipped");
                log.warn("⚠️ SKIPPED: " + methodName + " | Device: " + tag);
            }
        }

        // ✅ Step 3: Quit driver after test completion
        log.info("🛑 Quitting Appium driver after test method: " + methodName);
        DriverController.quitDriver();

        // ⭐ ADD SEPARATOR HERE
        log.info("----------------------------------------------------");
        log.info(" END OF TEST: " + methodName);
        log.info("----------------------------------------------------\n");

        extentTest.remove();
        //TL_DEVICE.remove();
    }

}


/* ░░░░░ Additional stuff for utility data ░░░░░ */
// 🔧 Future Enhancements: Utility setup, reporting hooks, and reusable test data loaders can be added here.
// For example, reading test data from Excel/JSON/YAML before tests, setting up listeners, etc.
// ---------------------------------------------------------------------------------------------------------------------

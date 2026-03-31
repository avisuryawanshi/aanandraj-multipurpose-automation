package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import listeners.ExtentReportManager;
import utils.ConfigReader;
import utils.DeviceSimUtils;
import utils.ScreenshotUtil;

import java.lang.reflect.Method;

/**
 * 📦 BaseTest - (Core base class) Abstract base class for all test classes
 * Handles driver setup, ExtentReports, logging, load properties files, screenshots, video recording, and TestNG lifecycle.
 */

public class BaseTest {

//  ░░░░░ Global Test Objects ░░░░░ ====================================================================================

    protected AppiumDriver mobileDriver;    // AppiumDriver variable for mobile testing

    // Log4j and Extent Objects - Manages entire report config
    protected static ExtentReports extent;                                       // Responsible for common info in the report
    protected static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();   // Responsible for updating each test result
    private static final ThreadLocal<String> TL_DEVICE = new ThreadLocal<>();
    protected static final Logger log = LogManager.getLogger(BaseTest.class);

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

    // Before Class - Setup before each test class (can use group filters) Setup driver, env configs, data loaders
    @BeforeClass(alwaysRun = true)
    @Parameters({"platform", "deviceName"}) // Optional: from XML
    public void setUp(@Optional String platform, @Optional String deviceName) {
        mobileDriver = DriverFactory.getAppiumDriver();     // SINGLE DEVICE SETUP fallback
        if (deviceName != null) {
            TL_DEVICE.set(deviceName);
        }
    }

    // AfterClass 🔻 Cleanup driver after test class
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver(); // Quit driver after tests
        TL_DEVICE.remove(); // 🛠️ Correct place to cleanup class-level thread data
    }

// ░░░░░ TestNG Hooks - METHOD LEVEL ░░░░░ =============================================================================

    // Before Method - Create new Extent test node for each method
    @BeforeMethod(alwaysRun = true)
    public void createExtentNode(Method method) {
        ExtentTest test = extent.createTest(method.getName());
        extentTest.set(test);

        if (TL_DEVICE.get() != null) {
            extentTest.get().assignCategory(TL_DEVICE.get());
        }
    }

    /* ░░░░░ Screenshot if the test case has failed & Extent logging ░░░░░ */
    @AfterMethod(alwaysRun = true)
    public void updateExtentAndQuit(ITestResult result) {
        ExtentTest test = extentTest.get();
        String methodName = result.getName();
        String tag = TL_DEVICE.get();
        String nameWithDevice = (tag != null ? tag + "_" : "") + methodName;

        switch (result.getStatus()) {
            case ITestResult.FAILURE -> {
                // 🛠️ Capture screenshot and get the PATH
                String screenshotPath = ScreenshotUtil.captureScreenshot(mobileDriver, nameWithDevice);

                // 🛠️ Attach to Extent Report
                test.fail(result.getThrowable()).addScreenCaptureFromPath(screenshotPath);
                //log.error("❌ FAILED: " + methodName + " | Device: " + tag);
            }
            case ITestResult.SUCCESS -> {
                test.pass("✅ Test passed");
                //log.info("✅ PASSED: " + methodName + " | Device: " + tag);
            }
            case ITestResult.SKIP -> {
                test.skip("⚠️ Test skipped");
                //log.warn("⚠️ SKIPPED: " + methodName + " | Device: " + tag);
            }
        }
        extentTest.remove();
        TL_DEVICE.remove();
    }

}


/* ░░░░░ Additional stuff for util  ity data ░░░░░ */
// 🔧 Future Enhancements: Utility setup, reporting hooks, and reusable test data loaders can be added here.
// For example, reading test data from Excel/JSON/YAML before tests, setting up listeners, etc.
// ---------------------------------------------------------------------------------------------------------------------



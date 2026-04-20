package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import listeners.ExtentReportManager;

import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ScreenshotUtil;

import java.lang.reflect.Method;

public class MultiDeviceBaseTest {

// ---------------------------------------------------------------------------------------------------------------------
// ✅ Appium driver to interact with mobile application (Android/iOS)
// ---------------------------------------------------------------------------------------------------------------------

// ---------------------------------------------------------------------------------------------------------------------
// ✅ Base class for running the same test class on multiple devices in parallel.
//  • Launches a driver per method (parameters come from testng.xml)
//  • Integrates with ExtentReports (thread‑safe)
//  • Captures screenshot on failure
// ---------------------------------------------------------------------------------------------------------------------

/* ═════════════  Extent Objects  ═════════════ */
        protected static ExtentReports extent = ExtentReportManager.getInstance();
        private static final ThreadLocal<ExtentTest> EXTENT_TEST = new ThreadLocal<>();
        private static final ThreadLocal<String>     TL_DEVICE = new ThreadLocal<>();

        /* ═════════════  Driver Helpers  ═════════════ */
        protected AppiumDriver driver() {
            return DriverController.getDriver();
        }

        // ── Create a node for each test method
        @BeforeMethod(alwaysRun = true)
        public void createExtentNode(Method method) {
            EXTENT_TEST.set(extent.createTest(method.getName()));
        }

        // ── Launch driver using parameters from <test> block & set device tag ──
        @Parameters({"platformName", "deviceName", "udid", "platformVersion", "automationName"})
        @BeforeMethod(alwaysRun = true, dependsOnMethods = "createExtentNode")
        public void launchDriver(String platformName,
                                 String deviceName,
                                 String udid,
                                 String platformVersion,
                                 String automationName) {

            // Compose tag e.g. Pixel6_emulator-5554
            String deviceTag = deviceName.replaceAll("\\s+", "") + "_" + udid;
            TL_DEVICE.set(deviceTag);

            DriverController.launchAppiumDriverWithExistingServer(platformName, deviceName, udid, platformVersion, automationName); //launchAppiumDriver
        }

        // ── Screenshot & Extent logging ──
        @AfterMethod(alwaysRun = true)
        public void updateExtentAndQuit(ITestResult result) {

            ExtentTest test = EXTENT_TEST.get();
            String     tag    = TL_DEVICE.get();
            String     method = result.getName();

            switch (result.getStatus()) {
                case ITestResult.FAILURE -> {
                    // screenshot file = tag_method.png
                    String file = ScreenshotUtil.captureScreenshot(driver(), tag + "_" + method);
                    test.fail(result.getThrowable()).addScreenCaptureFromPath(file);
                    String path = ScreenshotUtil.captureScreenshot(driver(), result.getName());
                    test.fail(result.getThrowable()).addScreenCaptureFromPath(path);
                }
                case ITestResult.SUCCESS -> test.pass("Test passed");
                case ITestResult.SKIP    -> test.skip("Test skipped");
            }

            DriverController.quitDriver();
            EXTENT_TEST.remove();
            TL_DEVICE.remove();
        }

        // Flush report exactly once per suite
        @AfterSuite(alwaysRun = true)
        public void flushExtent() {
            if (extent != null) extent.flush();
        }
    }

// ---------------------------------------------------------------------------------------------------------------------
// ✅ WITHOUT EXTENT REPORT AND SCREENSHOT
// ---------------------------------------------------------------------------------------------------------------------
/** Launch driver with parameters supplied by each <test> block */
        /*@Parameters({"platformName", "deviceName", "udid", "platformVersion", "automationName"})
        @BeforeMethod(alwaysRun = true)
        public void setUp(String platformName,
                          String deviceName,
                          String udid,
                          String platformVersion,
                          String automationName) {

            DriverController.launchAppiumDriver(platformName, deviceName, udid, platformVersion, automationName);}

        protected AppiumDriver driver() {
            return DriverController.getDriver();
        }

        @AfterMethod(alwaysRun = true)
        public void tearDown ()
        {
            DriverController.quitDriver();
        }

}*/

// =========================================================================================================================================================================

   /* protected AppiumDriver mobileDriver;  //AppiumDriver variable for mobile testing

    @BeforeClass(groups= {"Sanity", "Regression", "Master", "DataDriven"})
    @Parameters({"platformName", "deviceName", "udid", "platformVersion", "automationName"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(String platformName, String deviceName, String udid, String platformVersion, String automationName) {

        // PARALLEL EXECUTION SETUP ===========================================================
        System.out.println("Running tests on device: " + deviceName);
        DriverController.launchAppiumDriver(platformName, deviceName, udid, platformVersion, automationName);

        if (mobileDriver == null) {
            throw new IllegalStateException("Appium Driver is null. Cannot continue test execution.");
        }*/

        // SERIAL EXECUTION SETUP ==============================================================
        /*
        @Parameters("deviceName")
        @BeforeMethod
        public void setUp(String deviceName) {
        mobileDriver = DriverController.getAppiumDriver(platformName, deviceName, udid, platformVersion, automationName);

            if (deviceName.equalsIgnoreCase("Android")) {
                // Set Android capabilities
            } else if (deviceName.equalsIgnoreCase("iOS")) {
                // Set iOS capabilities
            } else if (deviceName.equalsIgnoreCase("Tablet")) {
                // Set Tablet capabilities
            } else {
                throw new IllegalArgumentException("Invalid device: " + deviceName);
            }
         */


    //@AfterClass(groups= {"Sanity", "Regression", "Master", "DataDriven"})
    /*@AfterMethod(alwaysRun = true)
    public void tearDown()
    {
        if (mobileDriver != null) mobileDriver.quit(); // Quit driver after tests
    }

// ------------  Additional stuff for utilities data  ------------------------------------------------------------------
// 🔧 Future Enhancements: Utility setup, reporting hooks, and reusable test data loaders can be added here.
// For example, reading test data from Excel/JSON/YAML before tests, setting up listeners, Screenshot capture, report logging hooks, etc. etc.
// ---------------------------------------------------------------------------------------------------------------------

}*/

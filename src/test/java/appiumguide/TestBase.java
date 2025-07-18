package appiumguide;

import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.DeviceSimUtils;

/**
 * 🔧 TestBase Class – Common Setup & Teardown for Appium Mobile Tests
 * -------------------------------------------------------------------
 * 📌 Scenario:
 * This class provides a reusable setup and teardown method for all mobile automation test classes.
 * It initializes the AppiumDriver using the InstallAPKByDesiredCapabilities class,
 * which automatically installs the APK during session creation.
 *
 * 📌 Used For:
 * - Reusability across multiple test classes
 * - Cleaner test code (avoiding repetition)
 * - Managing Appium session lifecycle properly
 */

public class TestBase {

    // ✅ Log4j Logger instance for logging test lifecycle events
    private static final Logger log = LogManager.getLogger(TestBase.class);

// ---------------------------------------------------------------------------------------------------------------------
// ✅ Appium driver to interact with mobile application (Android/iOS)
// ---------------------------------------------------------------------------------------------------------------------
    protected AppiumDriver driver;  //AppiumDriver variable for mobile testing

    public void setUp()
    {
        log.info("🔧 [SETUP] Initializing Appium driver...");
        //driver = InstallAPKByDesiredCapabilities.getAppiumDriver();   // use this InstallAPKByDesiredCapabilities class as a * Driver factory *
        driver = DeviceSimUtils.getAppiumDriver();
        log.info("✅ [SETUP SUCCESS] Appium driver initialized and app is installed.");
    }
    public void tearDown()
    {
        //InstallAPKByDesiredCapabilities.quitDriver();
        DeviceSimUtils.quitDriver();
        log.info("✅ [TEARDOWN SUCCESS] Appium session ended cleanly.");
    }

}

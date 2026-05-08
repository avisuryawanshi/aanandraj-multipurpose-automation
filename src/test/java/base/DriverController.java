package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;

import java.net.URI;
import java.net.URL;
import java.time.Duration;

/// DriverController: Responsible only for driver creation and management, Appium server lifecycle is managed by AppiumServerManager

public class DriverController {

    // =================================================================================================================
    // ✅ CLASS VARIABLES: Thread-safe storage for driver instances
    // =================================================================================================================

    public static final ThreadLocal<AppiumDriver> mobileDriver = new ThreadLocal<>();     // ✅ Thread-safe driver instance store
    private static final Logger logger = LoggerFactory.getLogger(DriverController.class); // ✅ Logger for this class

    // =================================================================================================================
    // ✅ GETTER: Access current thread's driver
    // =================================================================================================================

    /**
     * Returns the current thread's Appium driver.
     *
     * @return AppiumDriver instance for current thread
     */
    public static AppiumDriver getDriver() {
        return mobileDriver.get();
    }
    // =================================================================================================================
    // ✅ DRIVER LAUNCH: Connect to existing/manually started Appium server (backward compatible)
    // =================================================================================================================

    /**
     * Launches Appium driver connecting to an existing/manually started Appium server.
     * This method creates a fresh driver session for each test method (method-level driver management).
     */

    public static void launchAppiumDriverWithExistingServer(String platformName, String deviceName, String udid, String platformVersion, String automationName) {

        // ✅ Ensure any existing driver is cleaned up before creating a new one
        quitDriver();

        try {
            // ✅ Use UiAutomator2Options (W3C-compliant) with method-level optimizations
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName(ConfigReader.get("device.emulator.platformName"))
                    .setDeviceName(ConfigReader.get("device.emulator.name"))
                    .setUdid(ConfigReader.get("device.real.udid"))
                    .setPlatformVersion(ConfigReader.get("device.emulator.platformVersion"))
                    .setAutomationName(ConfigReader.get("device.emulator.automationName"))
                    .setAppPackage(ConfigReader.get("appPackage"))
                    .setAppActivity(ConfigReader.get("appActivity"))
                    .setAdbExecTimeout(Duration.ofSeconds(60)) // Increased timeout for ADB commands

                    // 🚀 HEADLESS PERFORMANCE OPTIMIZATIONS

                    // 🚀 Method-level optimizations for faster execution and stability in headless mode
                    // App State Management: (Test Isolation) Close driver after each test method and start fresh
                    //.setNoReset(false)                               // Don't reset app state between method calls (faster than no reset)
                    //.setFullReset(false)                             // Don't uninstall app between method calls (faster than full reset)
                    //.setNewCommandTimeout(Duration.ofSeconds(300))   //  Longer timeout for headless stability

                    // Emulator Performance Tweaks: Note: Headless mode is for Android emulators; if you're using real devices, it won't apply.
                    .amend("noWindow", true)          // 🚀 HEADLESS MODE: Run emulator without a visible window
                    //.amend("noWindow", false) // Switch to visible mode for debugging
                    .amend("avdArgs", "-no-audio -no-boot-anim") // 🚀 HEADLESS: AVD Performance Boost: Disable audio and skip boot animation
                    .amend("disableWindowAnimation", true) // 🚀 HEADLESS: Disable window, Disable animations for faster execution

                    // Use cautiously (only if the environment is stable)
                    //.amend("skipDeviceInitialization", true) // 🚀 HEADLESS: Skip device init checks
                    //.amend("skipServerInstallation", true) // 🚀 HEADLESS: Skip server installation

                    // UI Interaction Optimization: (Test Stability) Ignore non-interactive elements to speed up UI interactions in headless mode
                    .amend("ignoreUnimportantViews", true) // 🚀 HEADLESS: Ignore non-interactive elements
                    .amend("disableAndroidWatchers", true) // 🚀 HEADLESS: Disable Android watchers

                    // Optional optimization
                    .amend("ignoreUnimportantViews", true); // 🚀 HEADLESS: Ignore non-interactive elements for faster execution

            URL serverURL = new URI("http://127.0.0.1:4723/").toURL();
            AppiumDriver driver = new AndroidDriver(serverURL, options);
            mobileDriver.set(driver);

            logger.info("🚀 NEW Appium session started successfully for device: {} (method-level session)", deviceName);

        } catch (Exception e) {
            //logger.info("Explainable AI Root Cause Analysis: {}", RootCauseAnalyzer.analyzeRootCause(e));
            logger.error("❌ Failed to start NEW Appium session for device: {} (method-level session)", udid, e);
            throw new RuntimeException("Failed to launch Appium driver for method-level session", e);
        }
    }

// =================================================================================================================
// ✅ CLEANUP: Quit driver if exists (for method-level management)
// =================================================================================================================

    /**
     * Quits the current driver session if it exists.
     * This method is used for cleaning up existing sessions before creating new ones (method-level management).
     */
    public static void quitDriver() {
        AppiumDriver driver = mobileDriver.get();
        if (driver != null) {
            try {
                logger.info("Quitting existing Appium session for cleanup...");
                driver.quit();
                logger.info("Existing Appium session quit successfully");
            } catch (Exception e) {
                logger.error("Failed to quit existing Appium session", e);
            } finally {
                mobileDriver.remove();
            }
        }
    }

    // =================================================================================================================
    // ✅ UTILITY: Check driver status (for method-level management)
    // =================================================================================================================

    /**
     * Checks if there is an active driver session for the current thread.
     *
     * @return true if driver exists and is active, false otherwise
     */
    public static boolean isDriverActive() {
        AppiumDriver driver = mobileDriver.get();
        return driver != null;
    }

    /**
     * Gets the session ID of the current driver (useful for debugging method-level sessions).
     *
     * @return Session ID string or null if no active driver
     */
    public static String getCurrentSessionId() {
        AppiumDriver driver = mobileDriver.get();
        return driver != null ? driver.getSessionId().toString() : null;
    }
}

// =================================================================================================================
// ✅ DRIVER LAUNCH: Create driver and connect to Appium server (managed by AppiumServerManager)
// =================================================================================================================

/**
 * Launches an Appium driver for a specific device/thread.
 * The Appium server is automatically started by AppiumServerManager if not already running.
 *
 * @param platformName    Platform name (e.g., "Android")
 * @param deviceName      Device name (e.g., "Pixel 6 API 34")
 * @param udid            Device UDID (e.g., "emulator-5554")
 * @param platformVersion Platform version (e.g., "14")
 * @param automationName  Automation engine (e.g., "UiAutomator2")
 */
    /*public static void launchAppiumDriver(String platformName, String deviceName, String udid, String platformVersion, String automationName) {

        try {
            // ✅ If parameters are null, read from config-qa.properties
            if (platformName == null || deviceName == null || udid == null || platformVersion == null || automationName == null) {
                platformName = ConfigReader.get("device.emulator.platformName");
                deviceName = ConfigReader.get("device.emulator.name");
                udid = ConfigReader.get("device.emulator.udid");
                platformVersion = ConfigReader.get("device.emulator.platformVersion");
                automationName = ConfigReader.get("device.emulator.automationName");
            }

            // ✅ Get Appium server URL (server will be started automatically if not running)
            URL serverUrl = AppiumServerManager.getServiceUrl();

            // ✅ Use UiAutomator2Options (W3C-compliant)
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName(platformName)
                    .setDeviceName(deviceName)
                    .setUdid(udid)
                    .setPlatformVersion(platformVersion)
                    .setAutomationName(automationName)
                    .setAppPackage(ConfigReader.get("appPackage"))    // App package name from config
                    .setAppActivity(ConfigReader.get("appActivity")); // App activity from config, Entry activity

            // 👉 Optional: Use .setApp("path/to/app.apk") if fresh installation is needed

            // ✅ Create driver with server URL from AppiumServerManager
            AppiumDriver driver = new AndroidDriver(serverUrl, options);
            mobileDriver.set(driver);

            logger.info("Appium session started successfully for device: {}", deviceName);

        } catch (Exception e) {
            logger.error("Failed to start Appium session for device: {}", udid, e);
        }
    }*/

// =================================================================================================================
// ✅ CLEANUP: Quit driver (Appium server is managed separately by AppiumServerManager)
// =================================================================================================================

/**
 * Quits the current driver session after each test method.
 * This method is called in @AfterMethod to ensure clean driver shutdown (method-level management).
 */
/*public static void quitDriver() {
    AppiumDriver driver = mobileDriver.get();
    if (driver != null) {
        try {
            logger.info("🛑 Quitting Appium session after test method...");
            driver.quit();
            logger.info("✅ Appium session ended successfully after test method");
        } catch (Exception e) {
            logger.error("❌ Failed to quit Appium session after test method", e);
        } finally {
            mobileDriver.remove();
            logger.info("🧹 Driver reference cleaned up from ThreadLocal");
        }
    } else {
        logger.warn("⚠️ No active Appium session found to quit after test method");
    }
}*/

// ===========================================================================================================================================================================
// ✅ DEPRECATED: DesiredCapabilities approach for Appium <2.0 (kept for reference)
// ===========================================================================================================================================================================

/// NOTE: DesiredCapabilities APPIUM <2.0 WORKS BUT DEPRECATED

    /*public static AppiumDriver launchAppiumDriver(String platformName, String deviceName, String udid, String platformVersion, String automationName) {

        try
        {
            DesiredCapabilities cap = new DesiredCapabilities();

           // ✅ W3C-compliant capabilities
            cap.setCapability("platformName", platformName);
            cap.setCapability("appium:deviceName", deviceName);
            cap.setCapability("appium:udid", udid);
            cap.setCapability("appium:platformVersion", platformVersion);
            cap.setCapability("appium:automationName", automationName);
            cap.setCapability("appium:appPackage", "com.example.anandraj_multipuprose_hall");                // App package name
            cap.setCapability("appium:appActivity", "com.example.anandraj_multipuprose_hall.MainActivity");  // App activity, Entry activity

            // Platform details for "Pixel 6" (W3C standard capabilities)
            cap.setCapability("platformName", "Android");
            cap.setCapability("appium:deviceName", "sdk_gphone64_x86_64");
            cap.setCapability("appium:udid", "emulator-5554");
            cap.setCapability("appium:platformVersion", "14");
            cap.setCapability("appium:automationName", "UiAutomator2");

            // Connecting to local Appium Server running at default port
            System.out.println("[INFO] Launching Appium session...");

            return new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(), cap);

        }
        catch (Exception e)
        {
            System.err.println("[ERROR] Failed to start Appium session for device: " + udid);
            System.out.println("[ERROR] Device with UDID" + udid + "is not available or not booted.");

            e.printStackTrace();
            return null;
        }

    }*/

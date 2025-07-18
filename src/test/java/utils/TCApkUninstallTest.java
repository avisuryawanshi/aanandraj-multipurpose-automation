package utils;

import appiumguide.InstallAPKByCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TCApkUninstallTest {

    private static final Logger log = LogManager.getLogger(TCApkInstallDCapTest.class);
    private AppiumDriver driver;
    private final String appPackage = "com.example.anandraj_multipuprose_hall";

    // --------------------------------------------------------------------------------
    // Setup: Initialize Appium driver
    // --------------------------------------------------------------------------------
    @BeforeClass
    public void setup() {
        log.info("🚀 [SETUP] Initializing Appium Driver for installation test...");
        driver = InstallAPKByCapabilities.getAppiumDriver();
    }

    // --------------------------------------------------------------------------------
    // Test: Verify App installation
    // --------------------------------------------------------------------------------
    @Test(priority = 1)
    public void installAppViaDesiredCapabilitiesTest() {
        log.info("🧪 [TEST] Verifying APK installation via Desired Capabilities...");

        boolean isInstalled = ((AndroidDriver) driver).isAppInstalled(appPackage);
        log.info("📦 Is app '{}' installed? → {}", appPackage, isInstalled);

        Assert.assertTrue(isInstalled, "❌ App is not installed on the device!");
        log.info("✅ APK is installed successfully via desired capabilities.");
    }

    // --------------------------------------------------------------------------------
    // Test: Uninstall the app
    // --------------------------------------------------------------------------------
    @Test(priority = 1)
    public void uninstallApp() {
        log.info("🧹 [TEST] Uninstalling the app '{}' from the device...", appPackage);

        try {
            boolean wasUninstalled = ((AndroidDriver) driver).removeApp(appPackage);
            if (wasUninstalled) {
                log.info("🗑️ App '{}' uninstalled successfully.", appPackage);
            } else {
                log.warn("⚠ App '{}' could not be uninstalled or was not present.", appPackage);
            }
        } catch (Exception e) {
            log.error("❌ Exception occurred while uninstalling the app: {}", e.getMessage());
        }
    }

    // --------------------------------------------------------------------------------
    // Cleanup: Quit Appium driver session
    // --------------------------------------------------------------------------------
    @AfterClass
    public void tearDown() {
        log.info("🔚 [TEARDOWN] Quitting the Appium session...");
        InstallAPKByCapabilities.quitDriver();
    }
}

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

public class TCApkInstallDCapTest {

    private static final Logger log = LogManager.getLogger(TCApkInstallDCapTest.class);
    private AppiumDriver driver;

    @BeforeClass
    public void setup() {
       driver = InstallAPKByCapabilities.getAppiumDriver();
    }

    @Test
    public void installAppViaDesiredCapabilitiesTest() {
        log.info("🧪 [TEST] Verifying APK installation via Desired Capabilities...");

        // Optional verification (if package is known)
        boolean isInstalled = ((AndroidDriver) driver).isAppInstalled("com.example.anandraj_multipuprose_hall");
        log.info("Is app installed? {}", isInstalled);
        Assert.assertTrue(isInstalled, "❌ App is not installed on the device!");
        log.info("✅ APK is installed successfully via desired capabilities.");
    }

    @AfterClass
    public void tearDown() {
        log.info("🔚 [TEARDOWN] Quitting the Appium session...");
        InstallAPKByCapabilities.quitDriver();
    }
}

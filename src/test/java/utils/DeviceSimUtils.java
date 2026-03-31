package utils;

import appiumguide.TestBase;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.PowerACState;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URL;

/**
 * 🔧 AppiumConceptsTest
 * --------------------------------------------------------------------------------
 * This class contains various emulator-based Appium features like:
 * - Plug/Unplug charger simulation
 * - Wi-Fi/Mobile Data toggling
 * - GPS location toggle
 * - GSM signal strength simulation
 * --------------------------------------------------------------------------------
 * ❗ NOTE: These tests are meant for EMULATORS ONLY (not physical devices).
 */

public class DeviceSimUtils extends TestBase {

    private static final Logger log = LogManager.getLogger(DeviceSimUtils.class);
    private static AndroidDriver driver;

    // -----------------------------------------------------------------------------------------------------------------
    // 🔄 Setup: Launch Appium session and initialize AndroidDriver for emulator
    // -----------------------------------------------------------------------------------------------------------------
    @BeforeTest
    public static AppiumDriver getAppiumDriver() {
        if (driver == null) {
            try {
                // ✅ Define driver options using UiAutomator2Options (W3C)
                UiAutomator2Options options = new UiAutomator2Options()
                        .setPlatformName("Android")
                        .setDeviceName("sdk_gphone64_x86_64")
                        .setUdid("emulator-5554")
                        .setPlatformVersion("14")
                        .setAutomationName("UiAutomator2");
                        //.setAppPackage("com.example.anandraj_multipuprose_hall")
                       // .setAppActivity("com.example.anandraj_multipuprose_hall.MainActivity");
                //.setNoReset(true);  // Optional if needed

                URL serverURL = new URI("http://127.0.0.1:4723/").toURL();
                log.info("[INFO] Starting Appium session using UiAutomator2Options on: {}", serverURL);

                driver = new AndroidDriver(serverURL, options);
                log.info("[SUCCESS] Appium session started successfully with W3C options.");

            } catch (Exception e) {
                log.error("[ERROR] Failed to initialize Appium driver.", e);
            }
        }
        return driver;
    }

    // =================================================================================================================
    // ✅ COMBINED TEST: All Emulator Simulation Actions in One
    // =================================================================================================================

    /**
     * /**
     *  * 🔧 AppiumConceptsTest - Combined Emulator Action Test
     *  * -------------------------------------------------------------------
     *  * One test method combining:
     *  * - Charger plug/unplug
     *  * - Wi-Fi toggle
     *  * - Mobile Data toggle
     *  * - Location (GPS) toggle
     *  * - GSM Signal Strength simulation
     *  */
    @Test
    public void testAllDeviceSimulations() throws InterruptedException {

        log.info("🚀 Starting Combined Emulator Simulation Test...");

        // 🔌 Charger Simulation
        log.info("🔌 Plugging in charger...");
        driver.setPowerAC(PowerACState.ON);
        log.info("🔋 Battery Info after plug-in: {}", driver.getBatteryInfo());
        Thread.sleep(2000);

        log.info("🔌 Unplugging charger...");
        driver.setPowerAC(PowerACState.OFF);
        log.info("🔋 Battery Info after unplug: {}", driver.getBatteryInfo());
        Thread.sleep(2000);

        // 📶 Wi-Fi Toggle
        log.info("📶 Toggling Wi-Fi OFF...");
        driver.toggleWifi();
        Thread.sleep(2000);

        log.info("📶 Toggling Wi-Fi ON...");
        driver.toggleWifi();
        Thread.sleep(2000);

        // 📡 Mobile Data Toggle
        log.info("📡 Toggling Mobile Data OFF...");
        driver.toggleData();
        Thread.sleep(2000);

        log.info("📡 Toggling Mobile Data ON...");
        driver.toggleData();
        Thread.sleep(2000);

        // 📍 Location (GPS) Toggle
        log.info("📍 Toggling GPS OFF...");
        driver.toggleLocationServices();
        Thread.sleep(2000);

        log.info("📍 Toggling GPS ON...");
        driver.toggleLocationServices();
        Thread.sleep(2000);

        // 📶 GSM Signal Strength Simulation
        log.info("📶 Setting GSM Signal Strength to 0 (None)...");
        driver.executeScript("mobile: gsmSignal", ImmutableMap.of("strength", "0"));
        Thread.sleep(3000);

        log.info("📶 Setting GSM Signal Strength to 4 (Great)...");
        driver.executeScript("mobile: gsmSignal", ImmutableMap.of("strength", "4"));
        Thread.sleep(3000);

        log.info("✅ All emulator simulation steps completed successfully.");
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Test: Simulate plugging and unplugging charger using PowerACState (emulator only)
    // 🔌 Scenario 1: Simulate plugging and unplugging charger
    // -----------------------------------------------------------------------------------------------------------------
    /*@Test
    public void testChargerPlugAndUnplugSimulation() throws InterruptedException {

        log.info("🔌 [TEST] Simulating charger plug/unplug using PowerACState...");
        try {
            // ✅ Plug in the charger (AC charging)
            driver.setPowerAC(PowerACState.ON);
            driver.getBatteryInfo();
            log.info("✅ Charger plugged in successfully. Battery Info: {}", driver.getBatteryInfo());

            Thread.sleep(2000); // Let the app respond to the charging state

            // ✅ Unplug the charger
            driver.setPowerAC(PowerACState.OFF);
            log.info("✅ Charger unplugged successfully. Battery Info: {}", driver.getBatteryInfo());
        } catch (Exception e) {
            log.error("⚠ Charger simulation failed. This feature works only on emulators.", e);
        }

        // battery info
        driver.getBatteryInfo();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 📶 Test: Enable and disable Wi-Fi using Appium built-in method
    // 📶 Scenario 2: Enable and Disable Wi-Fi at Runtime
    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void testEnableDisableWifi() throws InterruptedException {
        log.info("📶 [TEST] Disabling Wi-Fi...");
        driver.toggleWifi();
        Thread.sleep(3000);

        log.info("📶 [TEST] Enabling Wi-Fi...");
        driver.toggleWifi();
        Thread.sleep(3000);

        log.info("✅ Wi-Fi toggle test completed.");
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 📶 Test: Enabling & Disabling “Mobile Data” via Appium(Built-In Method) at Runtime
    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void testEnableDisableData() throws InterruptedException {
        log.info(" [TEST] Disabling Mobile Data...");
        driver.toggleData();
        Thread.sleep(3000);

        log.info("📶 [TEST] Enabling Mobile Data...");
        driver.toggleData();
        Thread.sleep(3000);

        log.info("✅ Mobile Data toggle test completed.");
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 📶 Test: Enable & Disable “Location Services” via Appium(Built-In Method)@ Runtime
    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void testEnableDisableLocationServicesGPS() throws InterruptedException {

        /// Enable & Disable “Location Services” (GPS-on/off)

        log.info("[TEST] Disabling Location Service(GPS-OFF)...");
        driver.toggleLocationServices();
        Thread.sleep(3000);

        log.info("[TEST] Enabling Location Service(GPS-ON)...");
        driver.toggleLocationServices();
        Thread.sleep(3000);

        log.info("✅ Location Services(GPS-on/off) toggle test completed.");
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 📶 Test: Appium command to manage “Signal Strength” Using (UiAutomator2 driver(emulators)
    // -----------------------------------------------------------------------------------------------------------------

    /**
    ℹ️ Valid GSM Signal Strength Levels: (for emulator testing):
    Value	Signal Strength
     *  0 - None
     *  1 - Poor
     *  2 - Moderate
     *  3 - Good
     *  4 - Great*/

    /*@Test
    public void testSignalStrength() throws InterruptedException {

        //Change the signal strength to '@' i.e., none
        log.info("[TEST] Change the signal strength to '@' i.e (None)");
        driver.executeScript("mobile: gsmSignal", ImmutableMap.of("strength", "0"));
        Thread.sleep(10000);

        //Change the signal strength to '4' i.e., great
        log.info("[TEST] Change the signal strength to '®' i.e (Great)");
        driver.executeScript("mobile: gsmSignal", ImmutableMap.of("strength", "4"));
    }*/

    // -----------------------------------------------------------------------------------------------------------------
    // // 🔚 Teardown: Quit Appium session after tests
    // -----------------------------------------------------------------------------------------------------------------
    @AfterTest
    public static void quitDriver() {
        if (driver != null) {
            log.info("🛑 Quitting Appium session...");
            driver.quit();
            driver = null;
        }
    }
}

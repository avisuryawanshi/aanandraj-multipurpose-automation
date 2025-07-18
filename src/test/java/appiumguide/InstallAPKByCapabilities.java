package appiumguide;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URI;
import java.net.URL;

public class InstallAPKByCapabilities {

/*
* ✅ 1. Through Desired Capabilities (Recommended for Most Cases)
* Appium can automatically install the APK before starting the session if you provide the app path:
*/

/**
* ✅ Modern Approach to Install APK using UiAutomator2Options (W3C Compliant)
* Appium automatically installs the APK before launching the session.
* ⚠️ Make sure you are using Appium 2.x and Java Client 8.3.0+ or higher.
*/
    private static AppiumDriver driver;

    public static AppiumDriver getAppiumDriver() {
        if (driver == null) {
            try {
                // ✅ Setup Appium options using W3C-compliant UiAutomator2Options

                // -------------------------------
                // Basic Android Capabilities
                // -------------------------------

                UiAutomator2Options options = new UiAutomator2Options()
                        .setPlatformName("Android")                  // Platform name, Mobile OS
                        .setDeviceName("sdk_gphone64_x86_64")        // Device name, Emulator name-sdk_gphone64_x86_64
                        .setUdid("emulator-5554")                    // Device ID-emulator-5554
                        .setPlatformVersion("14")                    // Android OS version
                        .setAutomationName("UiAutomator2")           // Appium Automation engine - Espresso, Selendroid(older Android versions below 5.0)
                // ✅ ========================================================================================================================
                        .setApp("D:\\AMH\\AnandRaj Multipurpose Hall Documents\\Builds\\app-release.apk");
                // ✅ ========================================================================================================================
                        //.setNoReset(true); // Optional: prevent reinstall between runs

                // -------------------------------
                // Connect to Appium Server URL
                // -------------------------------
                //return new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(), cap);
                URL serverURL = new URI("http://127.0.0.1:4723/").toURL();
                System.out.println("[INFO] Starting Appium session using UiAutomator2Options on: " + serverURL);

                driver = new AndroidDriver(serverURL, options);
                System.out.println("[SUCCESS] Appium session started and APK installed successfully.");

            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("[ERROR] Failed to initialize Appium driver.");
            }

        }
        return driver;
    }
    // --------------------------------------------------------------
    // Quits the current Appium driver session and resets the instance.
    // --------------------------------------------------------------
    public static void quitDriver() {
        if (driver != null) {
            System.out.println("[INFO] Quitting Appium session...");
            driver.quit();
            driver = null;
        }
    }
}


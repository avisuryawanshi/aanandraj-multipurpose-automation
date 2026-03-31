package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URI;
import java.net.URL;

/// NOTE: We prefer UiAutomator2Options over DesiredCapabilities in modern Appium

public class DriverController {

    // ✅ Thread-safe driver instance store
    static final ThreadLocal<AppiumDriver> mobileDriver = new ThreadLocal<>();

    // ✅ Getter to access current thread's driver
    public static AppiumDriver getDriver() {
        return mobileDriver.get();
    }

    // ✅ Launch driver for specific device/thread
    public static void launchAppiumDriver(String platformName, String deviceName, String udid, String platformVersion, String automationName) {

        try {
            // ✅ Use UiAutomator2Options (W3C-compliant)
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName(platformName)
                    .setDeviceName(deviceName)
                    .setUdid(udid)
                    .setPlatformVersion(platformVersion)
                    .setAutomationName(automationName)
                    .setAppPackage("com.example.anandraj_multipuprose_hall")
                    .setAppActivity("com.example.anandraj_multipuprose_hall.MainActivity");

            // 👉 Optional: Use .setApp("path/to/app.apk") if fresh installation is needed

            URL serverURL = new URI("http://127.0.0.1:4723/").toURL();
            AppiumDriver driver = new AndroidDriver(serverURL, options);
            mobileDriver.set(driver);

            System.out.println("[SUCCESS] Appium session started for: " + deviceName);

        } catch (Exception e) {
            System.err.println("[ERROR] Failed to start Appium session for device: " + udid);
            e.printStackTrace();
        }
    }

    // ✅ Clean up driver for current thread
    public static void quitDriver() {
        AppiumDriver driver = mobileDriver.get();
        if (driver != null) {
            System.out.println("[INFO] Quitting Appium session...");
            driver.quit();
            mobileDriver.remove();
        }
    }
}

// ===========================================================================================================================================================================

/// NOTE: DesiredCapabilities APPIUM <2.0 WORKS BUT DEPRECATED

    /*public static  AppiumDriver launchAppiumDriver(String platformName, String deviceName, String udid, String platformVersion, String automationName) {

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
            System.out.println("[ERROR] Device with UDID " + udid + " is not available or not booted.");

            e.printStackTrace();
            return null;
        }

    }*/

package appiumguide;

// =====================================================================================================================
/// DriverFactory: Factory for creating and managing Appium driver instances
/// Appium server lifecycle is managed by AppiumServerManager
// =====================================================================================================================

public class DriverFactory {

    // =================================================================================================================
    // ✅ CLASS VARIABLES
    // =================================================================================================================

   /* public static AppiumDriver mobileDriver;
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    // =================================================================================================================
    // ✅ DRIVER FACTORY: Create and return Appium driver instance
    // =================================================================================================================

    public static AppiumDriver getAppiumDriver() {
        if (mobileDriver == null) {

            try {
                // ✅ Get Appium server URL (server will be started automatically if not running)
                URL serverUrl = AppiumServerManager.getServiceUrl();

                // --------------------------------------------
                // ✅ Using UiAutomator2Options (W3C-Compliant)
                // --------------------------------------------
                UiAutomator2Options options = new UiAutomator2Options();

                //options.setPlatformName("platformName", "Android");  //hardcoded values
                //AVOID hardcoding these values and used a ConfigReader utility that pulls device details and app paths from a qa.properties file
                options.setPlatformName(ConfigReader.get("device.emulator.platformName"));        //Android
                options.setDeviceName(ConfigReader.get("device.emulator.name"));                  //Pixel 6 API 34 //Redmi Note 12 Pro+ 5G //sdk_gphone64_x86_64 //adb-xyz123456
                options.setUdid(ConfigReader.get("device.emulator.udid"));                        //adb-cqrwkn89h6wocew8-pQXpCZ._adb-tls-connect._tcp // emulator-5554
                options.setPlatformVersion(ConfigReader.get("device.emulator.platformVersion"));  //14
                options.setAutomationName(ConfigReader.get("device.emulator.automationName"));    //UiAutomator2
                //options.setFullReset(false);                                                    //To Uninstall and Reinstall Every Time
                //options.setNoReset(true);                                                       //To Keep the App but cache and user data

                // If the app is already on the device, and you don't provide a .apk path in setApp(), Appium will simply launch the existing app.

                // Launch installed app via appPackage and appActivity
                options.setAppPackage(ConfigReader.get("appPackage"));               //com.example.anandraj_multipuprose_hall
                options.setAppActivity(ConfigReader.get("appActivity"));             //com.example.anandraj_multipuprose_hall.MainActivity
                // Use setApp() for a local .apk path OR setActivity/Package for an installed app
                // options.setApp(ConfigReader.get("appPath"));                      //config properties file
                // 👉 Uncomment below if you prefer fresh app install from APK
                // options.setApp("D:\\AMH\\AnandRaj Multipurpose Hall Documents\\Builds\\app-release.apk");

                // ----------------------------------------------------
                // ✅ Connect to Appium Server (using AppiumServerManager)
                // ----------------------------------------------------
                mobileDriver = new AndroidDriver(serverUrl, options);

                logger.info("Appium driver created successfully");

            } catch (Exception e) {
                logger.error("Failed to initialize Appium driver", e);
            }
        }
        return mobileDriver; // returns null if exception occurred
    }

    // =================================================================================================================
    // ✅ CLEANUP: Quit driver (Appium server is managed separately by AppiumServerManager)
    // =================================================================================================================

    public static void quitDriver() {
        if (mobileDriver != null) {
            logger.info("Quitting Appium session...");
            mobileDriver.quit();
            mobileDriver = null;
            logger.info("Appium session ended successfully");
        }
    }
}*/

// ===========================================================================================================================================================================
// ✅ DEPRECATED: DesiredCapabilities approach for Appium <2.0 (kept for reference)
// ===========================================================================================================================================================================

/// NOTE: DesiredCapabilities APPIUM <2.0 WORKS BUT DEPRECATED

    /*private static AppiumDriver mobileDriver;

    // Handles the initialization of AppiumDriver (for mobile apps) and WebDriver (for browsers).
    public static AppiumDriver getAppiumDriver() {
        if (mobileDriver == null) {
            try {
                DesiredCapabilities cap = new DesiredCapabilities();

                // -------------------------------
                // ✅ Basic Android Capabilities
                // -------------------------------

                // Platform details for "Redmi Note 12 Pro+ 5G" (W3C-compliant capability keys)
                cap.setCapability("platformName", "Android");                    // Platform name, Mobile OS
                cap.setCapability("appium:deviceName", "Redmi Note 12 Pro+ 5G"); // Device name, Emulator name-sdk_gphone64_x86_64
                cap.setCapability("appium:udid", "adb-cqrwkn89h6wocew8-pQXpCZ._adb-tls-connect._tcp"); // Device ID-emulator-5554
                cap.setCapability("appium:platformVersion", "14");               // Android OS version
                cap.setCapability("appium:automationName", "UiAutomator2");  // Appium Automation engine - Espresso, Selendroid(older Android versions below 5.0)

                // -------------------------------
                // ❌ Fresh App installation Through Desired Capabilities (Recommended for Most Cases)
                // -------------------------------
                // cap.setCapability("appium:app", "D:\\AMH\\AnandRaj Multipurpose Hall Documents\\Builds\\app-release.apk");

                // -------------------------------
                // ✅ App Installation or Launch (package and activity to launch)
                // (if app already installed)
                // -------------------------------
                cap.setCapability("appium:appPackage", "com.example.anandraj_multipuprose_hall");                // App package name
                cap.setCapability("appium:appActivity", "com.example.anandraj_multipuprose_hall.MainActivity");  // App activity, Entry activity

                // ✅ If you want to install app freshly from apk, use this instead:
                // cap.setCapability("app", "D:\AMH\AnandRaj Multipurpose Hall Documents\Builds\app-release.apk");

                // -------------------------------
                // ✅ Connect to Appium Server URL
                // Connecting to local Appium Server running at default port
                // -------------------------------
                //return new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(), cap);
                URL serverURL = new URI("http://127.0.0.1:4723/").toURL();
                System.out.println("[INFO] Starting Appium session on: " + serverURL);
                mobileDriver = new AndroidDriver(serverURL, cap);
                System.out.println("[SUCCESS] Appium session started successfully.");

            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("[ERROR] Failed to initialize Appium driver.");
            }

        }
        return mobileDriver;
    }
    // --------------------------------------------------------------
    // ✅ Quits the current Appium driver session and resets the instance.
    // Should be called in @AfterClass or @AfterSuite hooks.
    // --------------------------------------------------------------
        public static void quitDriver() {
            if (mobileDriver != null) {
                System.out.println("[INFO] Quitting Appium session...");
                mobileDriver.quit();
                mobileDriver = null;
            }
        }*/
    }

//================ Reading from config-qa.properties using ConfigReader =====================================================================================================

/*package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.ConfigReader;

import java.net.URI;
import java.net.URL;

public class DriverFactory {

    private static AppiumDriver mobileDriver;

    public static AppiumDriver getAppiumDriver() {
        if (mobileDriver == null) {
            try {
                ConfigReader.loadConfig();

                DesiredCapabilities cap = new DesiredCapabilities();

                cap.setCapability("platformName", ConfigReader.get("platformName"));
                cap.setCapability("appium:deviceName", ConfigReader.get("deviceName"));
                cap.setCapability("appium:udid", ConfigReader.get("udid"));
                cap.setCapability("appium:platformVersion", ConfigReader.get("platformVersion"));
                cap.setCapability("appium:automationName", ConfigReader.get("automationName"));

                if (Boolean.parseBoolean(ConfigReader.get("useApkInstall"))) {
                    cap.setCapability(MobileCapabilityType.APP, ConfigReader.get("appPath"));
                } else {
                    cap.setCapability("appium:appPackage", ConfigReader.get("appPackage"));
                    cap.setCapability("appium:appActivity", ConfigReader.get("appActivity"));
                }

                cap.setCapability(MobileCapabilityType.NO_RESET, true);
                cap.setCapability("newCommandTimeout", 300);

                URL serverURL = new URI("http://127.0.0.1:4723/").toURL();
                mobileDriver = new AndroidDriver(serverURL, cap);

            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("[ERROR] Failed to initialize Appium driver.");
            }
        }

        return mobileDriver;
    }

    public static void quitDriver() {
        if (mobileDriver != null) {
            mobileDriver.quit();
            mobileDriver = null;
        }
    }
}
*/
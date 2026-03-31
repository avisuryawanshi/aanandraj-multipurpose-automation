package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import utils.ConfigReader;

import java.text.SimpleDateFormat;
import java.util.Date;
import utils.ConfigReader;

/// Singleton class that creates and provides an {@link ExtentReports} instance.

/**
 * ExtentReportManager
 * -------------------
 * • Singleton factory for {@link ExtentReports}.
 * • Generates a time‑stamped HTML report (SparkReporter).
 * • Injects framework / device / CI metadata.
 * <p>
 * Typical call‑flow
 * ────────────────
 * 1) BaseTest {@code @BeforeSuite} → getInstance()
 * 2) Test methods log to Extent ➜ HTML updated in real‑time.
 * 3) BaseTest {@code @AfterSuite}  → extent.flush()
 */

public final class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;
    private static ExtentTest test;

    /**
     * Lazily create the Extent report once per JVM.
     */
    public static synchronized ExtentReports getInstance() {

        if (extent == null) {    // already initialized

            /* 1️⃣ File name with timestamp REPORT FILE PATH */
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportPath = "test-output/ExtentReport_" + timestamp + ".html";

            /* 2️⃣ Spark reporter config */
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setTheme(Theme.STANDARD); // Or Theme.DARK
            spark.config().setDocumentTitle("Automation Test Report");
            spark.config().setReportName("Appium Mobile Test Execution Report");
            spark.config().setEncoding("utf-8");
            spark.config().setTimelineEnabled(true);

            /* 3️⃣ Attach & populate system info */
            extent = new ExtentReports();
            extent.attachReporter(spark);

            // Framework & tester/ System Info
            extent.setSystemInfo("Framework", "Appium + TestNG");
            extent.setSystemInfo("Environment", System.getProperty("env", "QA"));
            extent.setSystemInfo("Tester", System.getProperty("tester", "Avi"));
            extent.setSystemInfo("Screenshot Path", "test-output/screenshots/");

            // Device Info/ platform (fallback values overridden via –D)
            /*extent.setSystemInfo("Platform", System.getProperty("platformName", "Android"));
            extent.setSystemInfo("Device Name", System.getProperty("deviceName", "Redmi Note 12+ Pro"));
            extent.setSystemInfo("Android Version", System.getProperty("platformVersion", "14"));
            extent.setSystemInfo("Automation", System.getProperty("automationName", "UiAutomator2"));
            extent.setSystemInfo("UDID", System.getProperty("udid", "adb-cqrwkn89h6wocew8-pQXpCZ._adb-tls-connect._tcp"));
            extent.setSystemInfo("App Package", System.getProperty("appPackage", "N/A"));
            extent.setSystemInfo("App Version", System.getProperty("appVersion", "1.0"));*/

            // Read all properties from configReader
            extent.setSystemInfo("Platform", System.getProperty("platformName", ConfigReader.get("platformName")));  //Android
            extent.setSystemInfo("Device Name", System.getProperty("deviceName", ConfigReader.get("deviceName")));   //sdk_gphone64_x86_64 //adb-xyz123456
            extent.setSystemInfo("Android Version", System.getProperty("platformVersion", ConfigReader.get("platformVersion")));  //14
            extent.setSystemInfo("Automation", System.getProperty("automationName", ConfigReader.get("automationName"))); //UiAutomator2
            extent.setSystemInfo("UDID", System.getProperty("udid", ConfigReader.get("udid")));   //adb-cqrwkn89h6wocew8-pQXpCZ._adb-tls-connect._tcp // emulator-5554
            extent.setSystemInfo("App Package", System.getProperty("appPackage", ConfigReader.get("appPackage")));   //com.example.anandraj_multipuprose_hall
            extent.setSystemInfo("App Version", System.getProperty("appVersion", ConfigReader.get("appVersion")));    //com.example.anandraj_multipuprose_hall.MainActivity
            extent.setSystemInfo("Appium Server",  System.getProperty("appVersion", ConfigReader.get("appiumServer"))); //http://127.0.0.1:4723/

            // -------- Time & Locale Info ---------------------------------------------------------------------------------
            extent.setSystemInfo("User TimeZone", System.getProperty("user.timezone"));
            extent.setSystemInfo("User Country", System.getProperty("user.country"));
            extent.setSystemInfo("User Language", System.getProperty("user.language"));

            // -------- Build / CI data (set in CI/CD)-specific or Optional Properties -------------------------------------
            extent.setSystemInfo("Build Number", System.getProperty("build.number", "N/A"));        //✅
            extent.setSystemInfo("Git Branch", System.getProperty("git.branch", "Not Specified"));
            extent.setSystemInfo("Git Commit ID", System.getProperty("git.commit", "Not Specified"));
            extent.setSystemInfo("Execution Trigger", System.getProperty("triggeredBy", "Manual"));

            // -------- Env variables (from shell or Jenkins/GitLab CI/CD) -------------------------------------------------
            extent.setSystemInfo("CI/CD Pipeline", System.getenv().getOrDefault("CI_PIPELINE_NAME", "Local Execution")); //✅
            extent.setSystemInfo("Jenkins Job Name", System.getenv().getOrDefault("JOB_NAME", "N/A"));
            extent.setSystemInfo("Jenkins Build ID", System.getenv().getOrDefault("BUILD_ID", "N/A"));
            extent.setSystemInfo("Jenkins URL", System.getenv().getOrDefault("JENKINS_URL", "N/A"));
            extent.setSystemInfo("Execution URL", System.getenv().getOrDefault("BUILD_URL", "D:\\AMH\\AnandRaj Multipurpose Hall Documents\\Builds\\app-release.apk"));
        }

        return extent;
    }

}


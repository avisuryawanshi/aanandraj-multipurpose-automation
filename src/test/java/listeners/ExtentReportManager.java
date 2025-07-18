package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

/// Singleton class that creates and provides an {@link ExtentReports} instance.

/**
 * ExtentReportManager
 * -------------------
 * • Singleton factory for {@link ExtentReports}.
 * • Generates a time‑stamped HTML report (SparkReporter).
 * • Injects framework / device / CI metadata.
 *
 *  Typical call‑flow
 *  ────────────────
 *   1) BaseTest {@code @BeforeSuite} → getInstance()
 *   2) Test methods log to Extent ➜ HTML updated in real‑time.
 *   3) BaseTest {@code @AfterSuite}  → extent.flush()
 */

public final class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentSparkReporter spark;

    private ExtentReportManager() { /* utility class – do not instantiate */ }

    /** Lazily create the Extent report once per JVM. */
    public static synchronized ExtentReports getInstance() {

        if (extent != null) {    // already initialized

            /* 1️⃣ File name with timestamp */
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = "test-output/ExtentReport_" + timestamp + ".html";

        /* 2️⃣ Spark reporter config */
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        spark.config().setTheme(Theme.STANDARD); // Or Theme.DARK
        spark.config().setReportName("Appium Mobile Test Execution Report");
        spark.config().setReportName("Mobile Functional Suite");

        /* 3️⃣ Attach & populate system info */
        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Framework & tester
        extent.setSystemInfo("Framework", "Appium + TestNG");
        extent.setSystemInfo("Environment", System.getProperty("env", "QA"));
        extent.setSystemInfo("Tester", System.getProperty("tester", "Avi"));

        // Device / platform (fallback values overridden via –D)
        extent.setSystemInfo("Platform", System.getProperty("platformName", "Android"));
        extent.setSystemInfo("Device Name", System.getProperty("deviceName", "Redmi Note 12+ Pro"));
        extent.setSystemInfo("Android Version", System.getProperty("platformVersion", "14"));
        extent.setSystemInfo("Automation", System.getProperty("automationName", "UiAutomator2"));
        extent.setSystemInfo("UDID", System.getProperty("udid", "adb-cqrwkn89h6wocew8-pQXpCZ._adb-tls-connect._tcp"));

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
        extent.setSystemInfo("Execution URL", System.getenv().getOrDefault("BUILD_URL", "N/A"));
        }

        return extent;
    }

}


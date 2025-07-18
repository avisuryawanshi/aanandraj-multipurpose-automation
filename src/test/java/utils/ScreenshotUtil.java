package utils;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 📸 ScreenshotUtil - Utility class for capturing screenshots in Appium automation.
 * <p>
 * This utility captures the current screen of the mobile application and saves the image
 * in the local project directory under the `/screenshots/` folder.
 * <p>
 * ✅ Captures screenshots only when called (e.g., on test failure)
 * ✅ Filenames include the test method name + timestamp for uniqueness
 * ✅ Logs the screenshot path or errors in the console
 * ✅ Saves screenshots in date-based folders (yyyy-MM-dd)
 * ✅ Supports timestamped filenames for uniqueness
 * ✅ Logs the file path for traceability
 * ✅ Can optionally return path for ExtentReports attachment
 * <p>
 * 🔁 Common usage: Call from @AfterMethod on test failure (BaseTest)
 */

public class ScreenshotUtil {

    private static final Logger log = LogManager.getLogger(ScreenshotUtil.class);

/**
 * Captures and saves a screenshot with test method name and timestamp.
 *
 * @param driver   AppiumDriver instance (AndroidDriver or IOSDriver)
 * @param testName Name of the test method (used in filename)
 */

    /*public static String captureScreenshot(AppiumDriver driver, String testName) {
        try {
            File src = driver.getScreenshotAs(OutputType.FILE);                                                         // Capture the current screen as a file
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());                      // Create a timestamp for unique filenames
            String path = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";       // Define the path to save the screenshot
            FileUtils.copyFile(src, new File(path));                                                                    // Copy the screenshot file to the target location
            System.out.println("[Screenshot] Saved at: " + path);
        } catch (Exception e) {
            System.err.println("[Screenshot] Failed: " + e.getMessage());                                              // Log error if screenshot capture fails
        }
        return testName;
    }*/

public static String captureScreenshot(AppiumDriver driver, String testName) {

    String filePath = null;
    try {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);  // Ensure cast to TakesScreenshot
        String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String timestamp = new SimpleDateFormat("HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";

        String dirPath = System.getProperty("user.dir") + "/screenshots/" + dateFolder;
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs(); // Create folder if not exists
        }

        filePath = dirPath + "/" + fileName;
        File dest = new File(filePath);
        FileUtils.copyFile(src, dest);

        log.info("[Screenshot] Saved: " + filePath);
        System.out.println("[Screenshot] Saved at: " + filePath);

    } catch (Exception e) {
        log.error("[Screenshot] Failed to capture: ", e);
        System.err.println("[Screenshot] Failed: " + e.getMessage());
    }
    return filePath;  // Return correct file path for reporting
}

// ================================================================================================================================================================
    // 🔁 Captures screenshot as a Base64 string.

    /*public static String captureScreenshotBase64(AppiumDriver driver) {
        try {
            String base64Image = driver.getScreenshotAs(OutputType.BASE64);
            System.out.println("[✅ Screenshot Captured as Base64]");
            return base64Image;
        } catch (Exception e) {
            System.err.println("[❌ Failed to capture screenshot as Base64] " + e.getMessage());
            return null;
        }
    }*/
// ================================================================================================================================================================

    // Capture a screenshot of a specific section
    // Captures a screenshot of a specific WebElement/MobileElement.






}

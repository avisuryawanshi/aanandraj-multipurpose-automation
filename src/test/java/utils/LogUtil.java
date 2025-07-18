package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {

    /**
     * Save Appium logs or logcat to a .log file per test.
     * @param driver   AppiumDriver instance
     * @param logType  logcat / server / client / driver
     * @param testName test case name
     * @return Path to saved log file (optional for reports)
     */
    public static String saveLogsToFile(AppiumDriver driver, String logType, String testName) {
        String logFilePath = "";
        try {
            LogEntries entries = driver.manage().logs().get(logType);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String folderPath = System.getProperty("user.dir") + "/logs/";
            File folder = new File(folderPath);
            if (!folder.exists()) folder.mkdirs();

            logFilePath = folderPath + testName + "_" + logType + "_" + timestamp + ".log";
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath));

            for (LogEntry entry : entries) {
                writer.write(entry.getTimestamp() + " " + entry.getLevel() + ": " + entry.getMessage());
                writer.newLine();
            }

            writer.close();
            System.out.println("[✅ Log Saved] " + logFilePath);

        } catch (Exception e) {
            System.err.println("[❌ Failed to save logs] " + e.getMessage());
        }
        return logFilePath;
    }
}

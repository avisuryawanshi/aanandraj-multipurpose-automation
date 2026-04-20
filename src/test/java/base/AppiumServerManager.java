package base;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;

import java.io.File;
import java.time.Duration;

// =====================================================================================================================
/// AppiumServerManager: Manages Appium server lifecycle (start/stop) using AppiumServiceBuilder
/// Configuration is read from config-qa.properties with fallback defaults.
// =====================================================================================================================

public class AppiumServerManager {

    private static final Logger logger = LoggerFactory.getLogger(AppiumServerManager.class);
    private static AppiumDriverLocalService appiumService;
    private static boolean serverStartedByManager = false;

    // =================================================================================================================
    // ✅ START SERVER: Build and start Appium server programmatically
    // =================================================================================================================

    public static void startServer() {
        if (appiumService != null && appiumService.isRunning()) {
            logger.info("Appium server is already running");
            return;
        }

        String host = getWithDefault("appium.server.host", "127.0.0.1");
        int port = Integer.parseInt(getWithDefault("appium.server.port", "4723"));
        int timeout = Integer.parseInt(getWithDefault("appium.server.timeout", "120"));

        logger.info("Starting Appium server on {}:{}", host, port);

        try {
            appiumService = AppiumDriverLocalService.buildService(
                    new AppiumServiceBuilder()
                            .withIPAddress(host)                              //.withIPAddress("127.0.0.1")  //Hard-coded
                            .usingPort(port)                                  //.usingPort(4723) //Hard-coded
                            .withTimeout(Duration.ofSeconds(timeout))         //.withTimeout(Duration.ofSeconds(120)) //Hard-coded
                            .withLogFile(new File("log/automation.log"))  // 🔥 KEY LINE Appium logs → logs/appium.log
                            .withArgument(GeneralServerFlag.SESSION_OVERRIDE) //.withArgument(GeneralServerFlag.LOG_LEVEL, "info") //Override existing sessions and set log level to info
            );
            appiumService.start();
            serverStartedByManager = true;
            logger.info("Appium server started successfully");
        } catch (Exception e) {
            logger.error("Failed to start Appium server", e);
            throw new RuntimeException("Failed to start Appium server", e);
        }
    }

    // =================================================================================================================
    // ✅ STOP SERVER: Stop the Appium server if started by this manager
    // =================================================================================================================

    public static void stopServer() {
        if (appiumService != null && appiumService.isRunning() && serverStartedByManager) {
            logger.info("Stopping Appium server...");
            appiumService.stop();
            appiumService = null;
            serverStartedByManager = false;
            logger.info("Appium server stopped");
        }
    }

    // =================================================================================================================
    // ✅ GET SERVICE URL: Returns the URL of the running Appium server
    // =================================================================================================================

    public static java.net.URL getServiceUrl() {
        if (appiumService == null || !appiumService.isRunning()) {
            startServer();
        }
        return appiumService.getUrl();
    }

    // =================================================================================================================
    // ✅ CHECK STATUS: Check if Appium server is running
    // =================================================================================================================

    public static boolean isServerRunning() {
        return appiumService != null && appiumService.isRunning();
    }

    // =================================================================================================================
    // ✅ RESET: Reset the manager state
    // =================================================================================================================

    public static void reset() {
        stopServer();
        appiumService = null;
        serverStartedByManager = false;
    }

    // =================================================================================================================
    // ✅ HELPER: Read config with fallback default
    // =================================================================================================================

    private static String getWithDefault(String key, String defaultValue) {
        try {
            return ConfigReader.get(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
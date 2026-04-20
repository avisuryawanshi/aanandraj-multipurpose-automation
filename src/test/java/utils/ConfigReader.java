package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader is a utility class to load and fetch configuration values
 * from the `config-qa.properties` file located in the test resources' folder.
 * This is used to externalize configuration like device info, platform version,
 * app path, or driver behavior flags (e.g., noReset, apk install toggle, etc.).
 */

public class ConfigReader {

    private static Properties p;

    public static void loadProperties() {
        try {
            System.out.println("[INFO] Loading configuration from config-qa.properties...");

            FileInputStream file = new FileInputStream("src/test/resources/config-qa.properties");
            p = new Properties();
            p.load(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {

        return p.getProperty(key);
    }
}

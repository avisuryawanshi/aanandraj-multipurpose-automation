package utils;

import io.appium.java_client.AppiumDriver;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class VideoRecorder {

    /*public static void startRecording(AppiumDriver driver) {
        driver.startRecordingScreen();
    }

    public static String stopRecording(String testName) {
        String video = driver.stopRecordingScreen();
        String path = "recordings/" + testName + "_" + System.currentTimeMillis() + ".mp4";
        try {
            Files.write(Paths.get(path), Base64.getDecoder().decode(video));
        } catch (Exception e) {
            System.out.println("Failed to save video: " + e.getMessage());
        }
        return path;
    }*/
}

package base;

public class RootCauseAnalyzer {

    /**
     * Automated root cause analysis using explainable AI (rule-based for simplicity; can be extended with ML models).
     *
     * @param exception The exception to analyze
     * @return Explanation of the prediction reason and root cause
     */

    public static String analyzeRootCause(Exception exception) {
        Throwable rootCause = exception;
        while (rootCause.getCause() != null) {
            rootCause = rootCause.getCause();
        }
        String message = rootCause.getMessage().toLowerCase();
        if (message.contains("unable to connect")) {
            return "Prediction: Connection failure. Root cause: Appium server not running or network issue. Suggested fix: Start Appium server.";
        } else if (message.contains("device not found") || message.contains("udid")) {
            return "Prediction: Device unavailable. Root cause: Emulator not started or UDID mismatch. Suggested fix: Verify device and UDID.";
        } else if (message.contains("app not installed") || message.contains("app activity")) {
            return "Prediction: App launch failed. Root cause: App not installed or activity incorrect. Suggested fix: Install app or check activity.";
        } else {
            return "Prediction: Unknown error. Root cause: " + rootCause.getMessage() + ". Suggested fix: Review configuration.";
        }
    }

}

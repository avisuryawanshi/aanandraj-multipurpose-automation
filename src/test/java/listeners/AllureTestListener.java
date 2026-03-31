package listeners;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureTestListener implements ITestListener {


    @Override
    public void onTestFailure(ITestResult result) {
        saveFailureScreenshot("Failure Screenshot");
        saveTextLog("Test Failed: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        saveTextLog("Test Passed: " + result.getName());
    }

    @Attachment(value = "{0}", type = "image/png")
    public byte[] saveFailureScreenshot(String name) {
        // Replace with actual screenshot capture logic
        return new byte[0];
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    // Implement other methods if needed
}
//======================================================================================================================

/*@Override
public void onTestFailure(ITestResult result) {
    AppiumDriver driver = BaseTest.getDriver(); // Using ThreadLocal driver from BaseTest
    if (driver != null) {
        saveFailureScreenshot(driver);
    }
    saveTextLog("❌ Test Failed: " + result.getName());
}

@Override
public void onTestSuccess(ITestResult result) {
    saveTextLog("✅ Test Passed: " + result.getName());
}

@Attachment(value = "Failure Screenshot", type = "image/png")
public byte[] saveFailureScreenshot(AppiumDriver driver) {
    return driver.getScreenshotAs(OutputType.BYTES);
}

@Attachment(value = "{0}", type = "text/plain")
public static String saveTextLog(String message) {
    return message;
}
}*/
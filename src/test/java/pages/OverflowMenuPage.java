package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;

public class OverflowMenuPage extends BasePage {

// ---------------------------------------------------------------------------
// Constructor
// ---------------------------------------------------------------------------
    public OverflowMenuPage(AppiumDriver driver) {
        super(driver);
    }

// =====================================================================================================================
// LOCATORS ******
// =====================================================================================================================

    // Loading Home page elements
    final By hallLogoLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.ImageView\")");
    final By topTitleLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Anandraj Multipurpose Hall\")");
    //final By topBookingLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)");

    //CLICK OVERFLOW MENU
    final By overflowMenuLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(2)");
    //final By overflowMenuLoc = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button[3]");
    // xpath: //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button[3]
    //DOWNLOAD REPORTS
    final By downloadReportsLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Download Reports\")");
    final By selectDurationLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Duration\")");
    final By oneMonthLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"1 month\")");
    final By threeMonthsLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"3 months\")");
    final By sixMonthsLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"6 months\")");
    final By oneYearLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"1 year\")");
    final By submitBtnLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Submit\")");
    final By cancelBtnLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Cancel\")");

    //SHARE To...
    final By whatsApp = new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"android:id/icon\").instance(6)");
    final By cancelShareBtnLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"android:id/button2\")");
    //Select App
    final By selectAppLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"WhatsApp\")");
    final By cancelSelectAppBtnLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"android:id/button2\")");

    // SETTINGS
    final By settingsLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Settings\")");
    final By userInfoLoc = By.xpath("//android.widget.Button[contains(@content-desc, 'User Info')]");
    final By appInfoLoc = By.xpath("//android.view.View[contains(@content-desc, 'App Info')]");
    final By backToHomePage = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Back\")");

// =====================================================================================================================
// ACTIONS ******
// =====================================================================================================================

    public void isHallLogoVisible() {wait.until(ExpectedConditions.visibilityOfElementLocated(hallLogoLocator)).isDisplayed();}
    public void isTitleDisplayed() {wait.until(ExpectedConditions.visibilityOfElementLocated(topTitleLocator)).isDisplayed();}

    public void clickOverflowMenu(){wait.until(ExpectedConditions.elementToBeClickable(overflowMenuLoc)).click();}

    public void downloadReport(String duration) {
        wait.until(ExpectedConditions.elementToBeClickable(downloadReportsLoc)).click();
        wait.until(ExpectedConditions.elementToBeClickable(selectDurationLoc)).click();

        switch (duration.toLowerCase()) {
            case "1 month":
                wait.until(ExpectedConditions.elementToBeClickable(oneMonthLoc)).click();
                break;
            case "3 months":
                wait.until(ExpectedConditions.elementToBeClickable(threeMonthsLoc)).click();
                break;
            case "6 months":
                wait.until(ExpectedConditions.elementToBeClickable(sixMonthsLoc)).click();
                break;
            case "1 year":
                wait.until(ExpectedConditions.elementToBeClickable(oneYearLoc)).click();
                break;
            default:
                throw new IllegalArgumentException("Invalid duration: " + duration);
        }

        wait.until(ExpectedConditions.elementToBeClickable(submitBtnLoc)).click();
        // Hide keyboard after entering value (optional)
        ((AndroidDriver) mobileDriver).hideKeyboard();

    }

    public void cancelDownload() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelBtnLoc)).click();
    }

    public void shareViaWhatsApp() {
        wait.until(ExpectedConditions.elementToBeClickable(whatsApp)).click();
        wait.until(ExpectedConditions.elementToBeClickable(cancelShareBtnLoc)).click();
    }

    /*public void Settings() {
        wait.until(ExpectedConditions.elementToBeClickable(settingsLoc)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(userInfoLoc)).isDisplayed();
        wait.until(ExpectedConditions.visibilityOfElementLocated(appInfoLoc)).isDisplayed();
        wait.until(ExpectedConditions.elementToBeClickable(backToHomePage)).click();
    }*/

    public Map<String, String> Settings() {
        Map<String, String> infoTexts = new HashMap<>();

        // Click on the Settings icon
        wait.until(ExpectedConditions.elementToBeClickable(settingsLoc)).click();

        // Capture User Info text
        WebElement userInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(userInfoLoc));
        String userInfoText = userInfo.getDomAttribute("content-desc");
        infoTexts.put("userInfo", userInfoText);

        // Capture App Info text
        WebElement appInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(appInfoLoc));
        String appInfoText = appInfo.getDomAttribute("content-desc");
        infoTexts.put("appInfo", appInfoText);

        // Navigate back to home
        wait.until(ExpectedConditions.elementToBeClickable(backToHomePage)).click();

        return infoTexts;
    }



}

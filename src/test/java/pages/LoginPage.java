package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage extends BasePage {

    // ---------------------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------------------
    public LoginPage(AppiumDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private final WebDriverWait wait;

// LOCATORS ******

    // ---------------------------------------------------------------------------
    // Locators - Positive Login Flow
    // ---------------------------------------------------------------------------
    private final By txtUserFieldLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
    private final By txtPasswordFieldLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
    private final By loginBtnLocator = AppiumBy.accessibilityId("Set Login");
    private final By noBtnLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"No\")");
    private final By yesBtnLocator = AppiumBy.accessibilityId("Yes");

    // Locators for UI Element Validation
    private final By appTitleLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Anandraj Multipurpose Hall\")");
    private final By heading = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Set Login Credentials\")");
    private final By userFieldVisible = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]");
    private final By passwordFieldVisible = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]");
    private final By loginBtnVisible = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Set Login\")");

    // Locator for Negative Login Scenarios

    // toast message
    private final By validationAlerts = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Enter Valid Passcode\")");
    private final By validationAlerts1 = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Passcode must be exactly 6 digits\")");

// ACTIONS ******

    // -----------------------------------------------------------------------------------------------------------------
    // Actions - Positive Login Flow
    // -----------------------------------------------------------------------------------------------------------------
    public void enterUser(String username) {
        WebElement txtUserField = wait.until(ExpectedConditions.presenceOfElementLocated(txtUserFieldLocator));
        txtUserField.click();
        txtUserField.clear();
        txtUserField.sendKeys(username);
    }

    public void enterPassword(String passcode) {
        WebElement txtPasswordField = wait.until(ExpectedConditions.presenceOfElementLocated(txtPasswordFieldLocator));
        txtPasswordField.click();
        txtPasswordField.clear();
        txtPasswordField.sendKeys(passcode);
    }

    public void clickLogin() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginBtnLocator));
        loginBtn.click();
    }

    public void handleFingerprintPromptNo() {
        WebElement noBtn = wait.until(ExpectedConditions.elementToBeClickable(noBtnLocator));
        noBtn.click();
    }

    public void handleFingerprintPromptYes() {
        WebElement noBtn = wait.until(ExpectedConditions.elementToBeClickable(yesBtnLocator));
        noBtn.click();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Action/Validation Methods
    // -----------------------------------------------------------------------------------------------------------------
    public boolean isAppTitleDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(appTitleLocator)).isDisplayed();
    }

    public boolean isHeadingDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(heading)).isDisplayed();
    }

    public boolean isUserFieldVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userFieldVisible)).isDisplayed();
    }

    public boolean isPasswordFieldVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFieldVisible)).isDisplayed();
    }

    public boolean isLoginButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtnVisible)).isDisplayed();
    }

    // Method to verify if the validation alert is displayed
    public boolean isValidationAlertDisplayed() {

        try
        {
            WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(validationAlerts));
            WebElement alert1 = wait.until(ExpectedConditions.visibilityOfElementLocated(validationAlerts1));

            // Print the message from content-desc (description)
            String alertMessage = alert.getDomAttribute("content-desc"); // Or use getText() if supported //getAttribute is deprecated
            System.out.println("Validation Alert Message: " + alertMessage);
            return alert.isDisplayed();
        }
        catch (Exception e)
        {
            System.out.println("Validation alert not displayed.");
            return false;
        }


    }
}





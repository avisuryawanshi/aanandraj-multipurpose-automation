package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    // ---------------------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------------------
    public LoginPage(AppiumDriver driver) {

        super(driver); // Pass driver to BasePage
    }

// LOCATORS ******

    // ---------------------------------------------------------------------------
    // Locators - Positive Login Flow
    // ---------------------------------------------------------------------------
    private final By usernameFieldLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
    private final By passwordFieldLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
    private final By loginBtnLocator = AppiumBy.accessibilityId("Set Login");

    // LOCATORS - Fingerprint Prompt
    private final By fingerprintYesButton = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"No\")");
    private final By fingerprintNoButton = AppiumBy.accessibilityId("Yes");

    // Locators for UI Element Validation
    private final By appTitleLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Anandraj Multipurpose Hall\")");
    private final By loginHeading = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Set Login Credentials\")");

    // Locator for Negative Login Scenarios

    // toast message
    private final By validationAlerts = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Enter Valid Passcode\")");
    private final By validationAlerts1 = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Passcode must be exactly 6 digits\")");

// ACTIONS ******

    // -----------------------------------------------------------------------------------------------------------------
    // Actions - Positive Login Flow
    // -----------------------------------------------------------------------------------------------------------------
    public void enterUser(String username) {
        WebElement txtUserField = wait.until(ExpectedConditions.presenceOfElementLocated(usernameFieldLocator));
        txtUserField.click();
        txtUserField.clear();
        txtUserField.sendKeys(username);

        /*getElement(usernameFieldLocator).click();
        getElement(usernameFieldLocator).clear();
        getElement(usernameFieldLocator).sendKeys(username);*/
    }

    public void enterPassword(String passcode) {
        WebElement txtPasswordField = wait.until(ExpectedConditions.presenceOfElementLocated(passwordFieldLocator));
        txtPasswordField.click();
        txtPasswordField.clear();
        txtPasswordField.sendKeys(passcode);
    }

    public void clickLogin() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginBtnLocator));
        loginBtn.click();
    }

    public void handleFingerprintPromptNo() {
        WebElement noBtn = wait.until(ExpectedConditions.elementToBeClickable(fingerprintYesButton));
        noBtn.click();
    }

    public void handleFingerprintPromptYes() {
        WebElement yesBtn = wait.until(ExpectedConditions.elementToBeClickable(fingerprintNoButton));
        yesBtn.click();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Action/Validation Methods
    // -----------------------------------------------------------------------------------------------------------------
    public boolean isAppTitleDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(appTitleLocator)).isDisplayed();
    }

    public boolean isHeadingDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeading)).isDisplayed();
    }

    public boolean isUserFieldVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(usernameFieldLocator)).isDisplayed();
    }

    public boolean isPasswordFieldVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFieldLocator)).isDisplayed();
    }

    public boolean isLoginButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtnLocator)).isDisplayed();
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





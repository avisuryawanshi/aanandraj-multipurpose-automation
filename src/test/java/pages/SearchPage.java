package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;

public class SearchPage extends BasePage{

// ---------------------------------------------------------------------------
// Constructor
// ---------------------------------------------------------------------------
    public SearchPage(AppiumDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    final WebDriverWait wait;

// =====================================================================================================================
// LOCATORS ******
// =====================================================================================================================

    // Loading Home page elements
    final By hallLogoLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.ImageView\")");
    final By topTitleLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Anandraj Multipurpose Hall\")");
    //final By topBookingLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)");

    final By searchLoc = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button[2]");
    //final By SearchPageLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\")");
    final By SearchPageLoc = By.xpath("//android.widget.EditText");
    final By getBookingInfoLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"John Lee\n" + "2025-07-02\n" + "12:45\")");
    final By getBookingDetailsLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.view.View\").instance(6)");
    final By backToSearchPageLoc = new AppiumBy.ByAccessibilityId("Back");
    final By backToHomePageLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)");

// =====================================================================================================================
// ACTIONS ******
// =====================================================================================================================

    public void isHallLogoVisible() {wait.until(ExpectedConditions.visibilityOfElementLocated(hallLogoLocator)).isDisplayed();}
    public void isTitleDisplayed() {wait.until(ExpectedConditions.visibilityOfElementLocated(topTitleLocator)).isDisplayed();}

    public void clickSearchTab(){wait.until(ExpectedConditions.elementToBeClickable(searchLoc)).click();}

    public void enterSearchText(String name) {
        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(SearchPageLoc)); //visibilityOfElementLocated
        searchBox.click();
        searchBox.sendKeys(name);

        // Trigger the keyboard's search action
        HashMap<String, Object> args = new HashMap<>();
        args.put("action", "search");
        ((AndroidDriver) mobileDriver).executeScript("mobile: performEditorAction", args);
    }
        //((AndroidDriver) mobileDriver).pressKey(new KeyEvent(AndroidKey.SEARCH));
        //((AndroidDriver) mobileDriver).pressKey(new KeyEvent(AndroidKey.DPAD_CENTER)); // simulate pressing Search on soft keyboard
        //((AndroidDriver) mobileDriver).pressKey(new KeyEvent(AndroidKey.ENTER));

        // Hide keyboard after entering value (optional)
        //((AndroidDriver) mobileDriver).hideKeyboard();

    public String getSearchResultText() {
        WebElement getResult = wait.until(ExpectedConditions.elementToBeClickable(getBookingInfoLoc));
        String resultText = getResult.getText();
        getResult.click();
        return resultText;
    }

    public void clickBookingDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(getBookingDetailsLoc)).click();
    }

    public void clickBackToSearchPage() {
        wait.until(ExpectedConditions.elementToBeClickable(backToSearchPageLoc)).click();
    }

    public void clickBackToHomePage() {
        wait.until(ExpectedConditions.elementToBeClickable(backToHomePageLoc)).click();
    }

    private boolean isElementVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}






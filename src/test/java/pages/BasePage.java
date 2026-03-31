package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static base.DriverFactory.mobileDriver;

// Parent class for all page objects, containing common page interactions.
// It encapsulates common WebDriver initialization and utility functions like wait, scroll, etc.
// 🔧 This class is designed for mobile testing using AppiumDriver.

public class BasePage {

// -------------------------------------------------------------------------
// ✅ Driver and Wait Declarations
// -------------------------------------------------------------------------

    protected AppiumDriver mobileDriver;   // Appium driver for mobile testing for all page classes
    protected WebDriverWait wait;          // Explicit wait to handle dynamic UI elements

// -------------------------------------------------------------------------
// ✅ Constructor for Appium (Mobile) to initialize the Page Object/ with PageFactory initialization
// -------------------------------------------------------------------------

    public BasePage(AppiumDriver driver) {
        System.out.println("[INFO] Initializing BasePage for mobile driver...");

        this.mobileDriver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Fixed!
    }
}
        // NOTE: using direct locators (By) NOT dependent on PageFactory bcz Slower in Appium (lazy loading issues)
        // Initialize all @FindBy elements in child classes
        // PageFactory.initElements(driver, this);
        // System.out.println("[SUCCESS] Page elements initialized via PageFactory.");


// Additional utility methods like waitForVisibility(), scrollToElement(), etc. can be added here.S
// -------------------------------------------------------------------------
// 🔧 Reusable methods like:
// - waitForVisibility()
// - scrollToElement()
// - isElementDisplayed()
// - tapOnElement()
// Can be defined here and used by all child page classes.
// -------------------------------------------------------------------------

// --- GENERIC INTERACTION WRAPPERS ---

    /*protected WebElement getElement(By txtUserFieldLocator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(txtUserFieldLocator));*/

// -------------------------------------------------------------------------
// 🔧 Common Interaction Methods
// -------------------------------------------------------------------------


// Tap on an element identified by locator

/*protected void tap(By locator) {
    WebElement element = waitForElementToBeClickable(locator);
    element.click();
}



// Tap on a WebElement directly

protected void tap(WebElement element) {
    waitForElementToBeClickable((By) element);
    element.click();
}


// Enter text into an input field

protected void enterText(By locator, String text) {
    WebElement element = waitForElementVisible(locator);
    element.clear();
    element.sendKeys(text);
}

// Enter text into a WebElement

protected void enterText(WebElement element, String text) {
    element.clear();
    element.sendKeys(text);
}


// Get text from an element

protected String getText(By locator) {
    WebElement element = waitForElementVisible(locator);
    return element.getText();
}



 // Get text from a WebElement

protected String getText(WebElement element) {
    return element.getText();
}


// Check if element is displayed

protected boolean isDisplayed(By locator) {
    try {
        WebElement element = waitForElementVisible(locator);
        return element.isDisplayed();
    } catch (Exception e) {
        return false;
    }
}


// Check if WebElement is displayed

protected boolean isDisplayed(WebElement element) {
    try {
        return element.isDisplayed();
    } catch (Exception e) {
        return false;
    }
}


// Check if element is clickable

protected boolean isClickable(By locator) {
    try {
        waitForElementToBeClickable(locator);
        return true;
    } catch (Exception e) {
        return false;
    }
}



// Get element attribute value

protected String getAttribute(By locator, String attributeName) {
    WebElement element = waitForElementVisible(locator);
    return element.getAttribute(attributeName);
}


// Get accessibility label/content-desc

protected String getAccessibilityLabel(By locator) {
    return getAttribute(locator, "content-desc");
}

// -------------------------------------------------------------------------
// ⏳ Wait Methods
// -------------------------------------------------------------------------


// Wait for element to be visible

protected WebElement waitForElementVisible(By locator) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
}


// Wait for element to be visible with custom timeout

protected WebElement waitForElementVisible(By locator, int timeoutSeconds) {
    return new WebDriverWait(mobileDriver, Duration.ofSeconds(timeoutSeconds))
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
}


// Wait for element to be clickable

protected WebElement waitForElementToBeClickable(By locator) {
    return wait.until(ExpectedConditions.elementToBeClickable(locator));
}


//Wait for element to be clickable with custom timeout

protected WebElement waitForElementToBeClickable(By locator, int timeoutSeconds) {
    return new WebDriverWait(mobileDriver, Duration.ofSeconds(timeoutSeconds))
            .until(ExpectedConditions.elementToBeClickable(locator));
}


// Wait for element to be present (not necessarily visible)

protected WebElement waitForElementPresent(By locator) {
    return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
}


// Wait for element to be invisible

protected void waitForElementInvisible(By locator) {
    wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
}


//Wait for element to disappear from DOM

protected void waitForElementStale(By locator) {
    wait.until(ExpectedConditions.stalenessOf(waitForElementPresent(locator)));
}

// -------------------------------------------------------------------------
// 📱 Mobile-Specific Methods (Scroll, Swipe)
// -------------------------------------------------------------------------


// Scroll down by swiping up

protected void scrollDown() {
    Dimension size = mobileDriver.manage().window().getSize();
    int startX = size.width / 2;
    int startY = (int) (size.height * 0.7);
    int endY = (int) (size.height * 0.3);

    performSwipe(startX, startY, startX, endY, 800);
}

/
// Scroll up by swiping down

protected void scrollUp() {
    Dimension size = mobileDriver.manage().window().getSize();
    int startX = size.width / 2;
    int startY = (int) (size.height * 0.3);
    int endY = (int) (size.height * 0.7);

    performSwipe(startX, startY, startX, endY, 800);
}


//Scroll until element is visible

protected void scrollToElement(By locator) {
    int maxScrolls = 10;
    for (int i = 0; i < maxScrolls; i++) {
        try {
            WebElement element = mobileDriver.findElement(locator);
            if (element.isDisplayed()) {
                return;
            }
        } catch (Exception e) {
            // Element not found, continue scrolling
        }
        scrollDown();
    }
    throw new NoSuchElementException("Element not found after scrolling: " + locator);
}


// Scroll to element using UiScrollable (Android only)

protected void scrollToElementUiAutomator(String uiSelectorText) {
    if (mobileDriver instanceof AndroidDriver) {
        By scrollLocator = AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" + uiSelectorText + ")");
        mobileDriver.findElement(scrollLocator);
    }
}


// Perform swipe gesture

protected void performSwipe(int startX, int startY, int endX, int endY, int durationMs) {
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence swipe = new Sequence(finger, 1);

    swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
    swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    swipe.addAction(finger.createPointerMove(Duration.ofMillis(durationMs), PointerInput.Origin.viewport(), endX, endY));
    swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

    mobileDriver.perform(List.of(swipe));
}


// Perform tap at specific coordinates

protected void tapAtCoordinates(int x, int y) {
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence tap = new Sequence(finger, 1);

    tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
    tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

    mobileDriver.perform(List.of(tap));
}*/




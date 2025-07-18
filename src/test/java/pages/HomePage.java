package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage{

    // ---------------------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------------------
    public HomePage(AppiumDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private final WebDriverWait wait;

// LOCATORS ******

    // -----------------------------------------------------------------------------------------------------------------
    // Locators - UI Element Validation (Smoke/UI Sanity Testing)
    // -----------------------------------------------------------------------------------------------------------------
    /* Goal: Ensure all essential UI components are present and correctly rendered.
🧪 Test Cases:
	• Verify the visibility of the top title: "AanandRaj Multipurpose Hall"
	• Verify tab labels: "Upcoming Events", "Draft Events", "Completed Events"
	• Verify the "No data to display" text is shown when there's no data.
	• Verify the presence of the bottom navigation icons: "Events" and "Expense".
      Verify the presence and clickability of the refresh floating action button (FAB). */

    private final By hallLogoLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.ImageView\")");
    private final By topTitleLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Anandraj Multipurpose Hall\")");
    private final By topBookingLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)");
    private final By topSearchingLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)");
    private final By topOverflowMenuLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(2)");
    private final By refreshBtnLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(3)");

    private final By upcomingEventsTabLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Upcoming Events\n" + "Tab 1 of 3\")");
    private final By draftEventsTabLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Draft Events\n" + "Tab 2 of 3\")");
    private final By completedEventsTabLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Completed Events\n" + "Tab 3 of 3\")");

    private final By noDataToDisplayLocator = AppiumBy.accessibilityId("No data to display");
    private final By eventsTabLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Events\n" + "Tab 1 of 2\")");
    private final By expenseTabLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Expense\n" + "Tab 2 of 2\")");

// ACTIONS ******

    // -----------------------------------------------------------------------------------------------------------------
    // Action/Validation Methods
    // -----------------------------------------------------------------------------------------------------------------

// SVG - Top Title, Top Booking SVG, Top Searching SVG, top-right corner 3 vertical dots (⋮), loader, Upcoming, Draft, Complete, Events and Expense Tab

    public boolean isHallLogoVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(hallLogoLocator)).isDisplayed();
    }

    public boolean isTitleDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(topTitleLocator)).isDisplayed();
    }

    public String getTitleText() {
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(topTitleLocator));
        return titleElement.getDomAttribute("content-desc"); // Use getText() if it returns visible text
    }

    public boolean isBookingDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(topBookingLocator)).isDisplayed();
    }

    public boolean isSearchingDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(topSearchingLocator)).isDisplayed();
    }

    public boolean isOverflowMenuDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(topOverflowMenuLocator)).isDisplayed();
    }
// ---------------------------------------------------------------------------
    public boolean isUpcomingEventsTabVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(upcomingEventsTabLocator)).isDisplayed();
    }

    public boolean isDraftEventsTabVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(upcomingEventsTabLocator)).isDisplayed();
    }

    public boolean isCompleteEventsTabVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(upcomingEventsTabLocator)).isDisplayed();
    }
// ----------------------------------------------------------------------------
    public boolean isRefreshBtnVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(refreshBtnLocator)).isDisplayed();
    }

    public boolean isExpenseTabVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(expenseTabLocator)).isDisplayed();
    }

    public boolean isEventsTabVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(eventsTabLocator)).isDisplayed();
    }

// Top Tabs NAVIGATION - Upcoming, Draft, Complete Clickable -----------------------------------------------------------
/*
✅ 2. Tab Navigation Testing
Goal: Verify that the user can switch between event tabs.
🧪 Test Cases:
	• Click "Draft Events" → check UI updates and relevant message or data.
	• Click "Completed Events" → assert the page content changes accordingly.
Switch back to "Upcoming Events" and confirm the view resets.
*/
    public void tapBookingBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(topBookingLocator)).click();
    }

    public void tapUpcomingEventsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(upcomingEventsTabLocator)).click();
    }

    public void tapDraftEventsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(draftEventsTabLocator)).click();
    }

    public void tapCompletedEventsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(completedEventsTabLocator)).click();
    }

// Bottom Tabs NAVIGATION - Expense, Events Clickable ------------------------------------------------------------------
/*
✅ 4. Bottom Navigation Testing
Goal: Validate navigation between main app sections.
🧪 Test Cases:
	• Tap on "Expense" tab → verify navigation and content for expense screen.
Tap back to "Events" → ensure you return to the Events screen.
*/
    public void tapExpenseTab() {
        wait.until(ExpectedConditions.elementToBeClickable(expenseTabLocator)).click();
    }

    public void tapEventsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(eventsTabLocator)).click();
    }


}

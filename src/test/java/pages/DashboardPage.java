package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class DashboardPage extends BasePage {

// ---------------------------------------------------------------------------
// Constructor
// ---------------------------------------------------------------------------
    public DashboardPage(AppiumDriver driver) {
        super(driver);
    }
// =====================================================================================================================
// LOCATORS ******
// =====================================================================================================================

    // Loading Home page elements
    final By hallLogoLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.ImageView\")");
    final By topTitleLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Anandraj Multipurpose Hall\")");

    final By loaderLoc = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button");
    //final By getRecordsLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.view.View\").instance(13)");

    // Fetch all event *** RECORDS ***
    final By upcomingEventCardsLoc = By.xpath("//android.view.View[@content-desc and (contains(@content-desc, '2025'))]");
    final By navigateToDraftLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Draft Events\n" + "Tab 2 of 3\")");
    final By draftEventCardsLoc = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]");
    //final By scrollSpecificDraftEventLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Srushti \n" + "2024-09-14\n" + "18:50\")");
    final By scrollSpecificDraftEventLoc = AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true))" + ".scrollIntoView(new UiSelector().description(\"Srushti \n" + "2024-09-14\n" + "18:50\"))");
    final By navigateToCompletedLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Completed Events\n" + "Tab 3 of 3\")");
    final By completedEventCardsLoc = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]");


// =====================================================================================================================
// ACTIONS ******
// =====================================================================================================================

    public void isHallLogoVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(hallLogoLocator)).isDisplayed();
    }

    public void isTitleDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(topTitleLocator)).isDisplayed();
    }

    public void refreshQueries() {
        wait.until(ExpectedConditions.elementToBeClickable(loaderLoc)).click();
    }

    // RECORDS COUNT ****** // Fetch all event records (assuming each is a card or container view)

    public void getUpcomingEventCount() {
        List<WebElement> upcomingEvents = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(upcomingEventCardsLoc));
        System.out.println("Total upcoming events: " + upcomingEvents.size());
    }

    public void navigateToDraftEvents() {
        wait.until(ExpectedConditions.elementToBeClickable(navigateToDraftLoc)).click();
    }

    // ** Scrolling Specific element (eg. Srushti) ------------------------------------------------------------------------

    public void scrollToAndClickDraftEvent(String fullDescription) {
        wait.until(ExpectedConditions.elementToBeClickable(scrollSpecificDraftEventLoc)).click();
        System.out.println("✅ Clicked on Draft Event: " + fullDescription);
    }

    /*public void scrollToAndClickDraftEvent(String fullDescription) {
        String uiSelector = "new UiScrollable(new UiSelector().scrollable(true))" + ".scrollIntoView(new UiSelector().description(\"" + fullDescription + "\"))";

        mobileDriver.findElement(AppiumBy.androidUIAutomator(uiSelector)).click();
        System.out.println("✅ Clicked on Draft Event: " + fullDescription);
    }*/
    // -----------------------------------------------------------------------------------------------------------------

    public void getDraftEventCount() {
        List<WebElement> draftEvents = (Collections.singletonList(wait.until(ExpectedConditions.presenceOfElementLocated(draftEventCardsLoc))));
        System.out.println("Total draft events: " + draftEvents.size());
    }

    public void navigateToCompletedEvents() {
        wait.until(ExpectedConditions.elementToBeClickable(navigateToCompletedLoc)).click();
    }

    public void getCompletedEventCount() {
        List<WebElement> completedEvents = (Collections.singletonList(wait.until(ExpectedConditions.presenceOfElementLocated(completedEventCardsLoc))));
        System.out.println("Total completed events: " + completedEvents.size());
    }


}

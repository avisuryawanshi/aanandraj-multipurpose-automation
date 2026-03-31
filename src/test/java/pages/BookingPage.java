package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BookingPage extends BasePage {

    // ---------------------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------------------
    public BookingPage(AppiumDriver driver) {
        super(driver);
    }

// =====================================================================================================================
// LOCATORS ******
// =====================================================================================================================

    final By hallLogoLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.ImageView\")");
    final By topTitleLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Anandraj Multipurpose Hall\")");
    final By topBookingLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)");

    // ********* BOOKING FORM *********

    final By customerNameLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
    final By addressLoc = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText[2]");
    final By mobileNoLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(2)");

    // DATE
    final By dateFieldLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.view.View\").instance(9)");
    // Date picker elements
    final By openDateEditLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)");  // Example: selecting 30
    final By EnterDateLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\")");
    final By confirmDateLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"OK\")");

    // TIME
    final By timeFieldLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.view.View\").instance(10)");
    final By selectTimeLoc = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button[1]");
    final By enterHourLoc = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]");
    final By enterMinuteLoc = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]");
    final By amLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"AM\")");
    final By pmLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"PM\")");

    final By okTimeBtnLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"OK\")");
    final By cancelTimeLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Cancel\")");

    // Event type
    final By eventTypeLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(3)");
    //final By eventTypeLoc = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText[4]");

    // CATERING
    final By cateringLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Catering\")");
    final By promisedItemsCatLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(2)");
    //final By promisedItemsCatLoc = By.xpath("//android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText[3]");

    //final By scrollSpecificDraftEventLoc = AppiumBy.androidUIAutomator(
        //    "new UiScrollable(new UiSelector().scrollable(true))" + ".scrollIntoView(new UiSelector().description(\"Srushti \n" + "2024-09-14\n" + "18:50\"))");

    final By amountForCatLoc = AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true))" + ".scrollIntoView(new UiSelector().className(\"android.widget.EditText\").instance(5))");

    //final By amountForCatLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(5)");
    //final By amountForCatLoc = By.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText[6]");

    // DECORATIONS
    final By decorationsLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Decorations\")");
    //final By promisedItemsDecLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(3)");
    final By promisedItemsDecLoc = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText[4]");
    final By amountForDecLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(4)");
    //final By amountForDecLoc = By.xpath("//android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText[5]");

    // PHOTOGRAPHY
    final By photographyLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Photography\")");
    // same locator as hall amount !!!
    //final By amountForPhotoLoc = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText[6]");

    // PANDIT
    final By panditLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Pandit\")");
    //final By amountForPanditLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className("android.widget.EditText").instance(4)");
    final By amountForPanditLoc = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText[5]");

    // AMOUNT SECTION
    final By hallAmountLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(5)");
    //final By hallAmountLoc = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText[6]");

    // Total Amount NOT REQUIRED ***
    //final By totalAmountLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.view.View\").instance(12)");

    // DUPLICATE LOCATORS ***
    //final By bookingAmountLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className("android.widget.EditText").instance(5)");
    final By bookingAmountLoc = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText[4]");
    // Balance Amount NOT REQUIRED ****
    final By balanceAmountLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().className(\"android.view.View\").instance(13)");

    // FOOTER SECTION
    final By sendToWhatsAppLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Send to WhatsApp\")");
    final By confirmBookingLoc = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Confirm Booking\")");

// =====================================================================================================================
// ACTIONS ******
// =====================================================================================================================

    public void isHallLogoVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(hallLogoLocator)).isDisplayed();
    }

    public void isTitleDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(topTitleLocator)).isDisplayed();
    }

    public void tapBookingBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(topBookingLocator)).click();
    }

    // ********* ACTIONS FOR BOOKING FORM *********

    public void enterCustomerName(String name) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(customerNameLoc));
        field.click();
        field.clear();
        field.sendKeys(name);
    }

    public void enterAddress(String address) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(addressLoc));
        field.click();
        field.clear();
        field.sendKeys(address);
    }

    public void enterMobileNumber(String mobile) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(mobileNoLoc));
        field.click();
        field.clear();
        field.sendKeys(mobile);
    }

//  DATE ---------------------------------------------------------------------------------------------------------------

    // Opens the date picker by tapping on the date field
    public void tapDateField() {
        wait.until(ExpectedConditions.elementToBeClickable(dateFieldLoc)).click();
    }

    // Opens the calendar/edit mode from the date picker
    public void tapDateEditIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(openDateEditLoc)).click();
    }

    // Selects a specific date from the calendar (e.g., "07/01/2025")
    public void EnterSpecificDate(String date) {
        WebElement EnterDate = wait.until(ExpectedConditions.elementToBeClickable(EnterDateLoc));
        EnterDate.clear();
        EnterDate.sendKeys(date);
    }

    // Confirms the selected date
    public void confirmDate() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmDateLoc)).click();
    }

    //  TIME ---------------------------------------------------------------------------------------------------------------
    public void openTimePicker() {
        wait.until(ExpectedConditions.elementToBeClickable(timeFieldLoc)).click();
    }

    public void selectTime() {
        wait.until(ExpectedConditions.elementToBeClickable(selectTimeLoc)).click();
    }

    public void enterHours(String hr) {
        WebElement hours = wait.until(ExpectedConditions.elementToBeClickable(enterHourLoc));
        hours.click();
        hours.clear();
        hours.sendKeys(hr);
    }

    public void enterMinutes(String min) {
        WebElement minute = wait.until(ExpectedConditions.elementToBeClickable(enterMinuteLoc));
        minute.click();
        minute.clear();
        minute.sendKeys(min);
    }

    public void selectAM() {
        wait.until(ExpectedConditions.elementToBeClickable(amLoc)).click();
    }

    public void selectPM() {
        wait.until(ExpectedConditions.elementToBeClickable(pmLoc)).click();
    }

    public void confirmTimeSelection() {
        wait.until(ExpectedConditions.elementToBeClickable(okTimeBtnLoc)).click();
    }

    // EVENT TYPE ----------------------------------------------------------------------------------------------------------
    public void enterEventType(String eventType) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(eventTypeLoc));
        field.click();
        field.clear();
        field.sendKeys(eventType);

        // Hide keyboard after entering value (optional)
        ((AndroidDriver) mobileDriver).hideKeyboard();
    }

    // ********* CATERING ********* ------------------------------------------------------------------------------------
    /*public void toggleCatering() {
        wait.until(ExpectedConditions.elementToBeClickable(cateringLoc)).click();
        // Hide keyboard after entering value (optional)
        ((AndroidDriver) mobileDriver).hideKeyboard();
    }

    public void enterPromisedItemsCatering(String items) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(promisedItemsCatLoc));
        field.click();
        field.clear();
        field.sendKeys(items);
        // Hide keyboard after entering value (optional)
        ((AndroidDriver) mobileDriver).hideKeyboard();
    }

    // ********* DECORATIONS ********* ---------------------------------------------------------------------------------
    public void toggleDecorations() {
        wait.until(ExpectedConditions.elementToBeClickable(decorationsLoc)).click();
    }

    public void enterPromisedItemsDecorations(String items) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(promisedItemsDecLoc));
        field.clear();
        field.sendKeys(items);
    }

    public void enterAmountForDecorations(String amount) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(amountForLoc));
        field.clear();
        field.sendKeys(amount);
    }

    // ********* PHOTOGRAPHY ********* ---------------------------------------------------------------------------------
    public void togglePhotography() {
        wait.until(ExpectedConditions.elementToBeClickable(photographyLoc)).click();
    }

    public void enterAmountForPhotography(String amount) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(amountForPhotoLoc));
        field.clear();
        field.sendKeys(amount);
    }

    // ********* PANDIT ********* --------------------------------------------------------------------------------------
    public void togglePandit() {
        wait.until(ExpectedConditions.elementToBeClickable(panditLoc)).click();
    }

    public void enterAmountForPandit(String amount) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(amountForPanditLoc));
        field.clear();
        field.sendKeys(amount);
    }*/

    // ********* AMOUNT SECTION ********* ------------------------------------------------------------------------------

    // APPROACH 1 **********
    /*public void enterHallAmount(String number) {
        wait.until(ExpectedConditions.elementToBeClickable(hallAmountLoc)).click();
        try {
            Thread.sleep(1000); // Let the UI update (optional but helps sometimes)
        } catch (InterruptedException ignored) {
        }
        //scrollDown(); // 👈 Scroll after toggling Catering
    }*/

    // APPROACH 2 ***********
    /*public void enterHallAmount(String number) {
        scrollToElement(hallAmountLoc);  // Ensure field is visible

        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(hallAmountLoc));
        field.click();
        field.sendKeys(number);
    }*/

    // APPROACH 3 ************
      /*public void enterHallAmount(String number) {
      for (int attempt = 0; attempt < 2; attempt++) {
          try {
              WebElement field = wait.until(ExpectedConditions.elementToBeClickable(hallAmountLoc));
              field.click();
              field.clear();
              field.sendKeys(number);
              ((AndroidDriver) mobileDriver).hideKeyboard();
              break; // success
          } catch (StaleElementReferenceException e) {
              System.out.println("Retrying due to stale element...");
          }
      }
      }*/

    // SCROLL SUPPORT
        /*public void scrollDown() {
        Dimension size = mobileDriver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.6);
        int endY = (int) (size.height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        mobileDriver.perform(List.of(swipe));
        }

    // Scroll until element is visible
        public void scrollToElement(By locator) {
        int maxScroll = 2;
        int attempt = 0;
        while (attempt < maxScroll) {
            try {
                WebElement element = mobileDriver.findElement(locator);
                if (element.isDisplayed()) {
                    return;
                }
            } catch (Exception ignored) {}
            scrollDown();  // Perform actual swipe
            attempt++;
         }
            throw new NoSuchElementException("Element not found after scrolling: " + locator.toString());
        }*/

    // APPROACH 4 **************
        /*public void enterHallAmount(String number) {
        //scrollToElement(hallAmountLoc);  // Ensure field is visible

        // Tap via TouchActions or JavaScript-like interaction
        WebElement field = wait.until(ExpectedConditions.presenceOfElementLocated(hallAmountLoc));

        try {
            Thread.sleep(1000); // Small delay for UI to stabilize
        } catch (InterruptedException ignored) {}

        field.click(); // Try clicking twice if needed
        field.click();

        try {
            Thread.sleep(500); // Wait after focus
        } catch (InterruptedException ignored) {}

        field.clear();
        field.sendKeys(number);

        System.out.println("Entered hall amount: " + number);
    }*/
// =====================================================================================================================
    // NOT REQUIRED Get Total Amount
    /*public String getTotalAmount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(totalAmountLoc)).getText();
    }*/

    public void enterBookingAmount(String amount) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(bookingAmountLoc));
        field.click();
        field.clear();
        field.sendKeys(amount);
    }

    // NOT REQUIRED Get Total Amount
    /*public String getBalanceAmount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(balanceAmountLoc)).getText();
    }*/

    // ********* FOOTER ACTIONS ********* ------------------------------------------------------------------------------
    /*public void tapSendToWhatsApp() {
        scrollToElement(sendToWhatsAppLoc);  // Ensure field is visible
        wait.until(ExpectedConditions.elementToBeClickable(sendToWhatsAppLoc)).click();
    }*/

    public void tapConfirmBooking() {
        //scrollToElement(confirmBookingLoc);  // Ensure field is visible
        wait.until(ExpectedConditions.elementToBeClickable(confirmBookingLoc)).click();
    }
}


// =====================================================================================================================
// SCROLL SUPPORT
    /*public void scrollDown() {
        Dimension size = mobileDriver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.6);
        int endY = (int) (size.height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        mobileDriver.perform(List.of(swipe));
    }*/

    /*public void scrollToElement(By locator) {
        int maxScroll = 5;  // prevent infinite loop
        int attempt = 0;
        while (attempt < maxScroll) {
            try {
                WebElement element = mobileDriver.findElement(locator);
                if (element.isDisplayed()) {
                    return; // found and visible
                }
            } catch (Exception ignored) {}

            scrollDown();
            attempt++;
        }
    }*/
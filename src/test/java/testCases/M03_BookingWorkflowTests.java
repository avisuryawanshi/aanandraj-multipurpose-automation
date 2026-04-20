package testCases;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BookingPage;
import pages.HomePage;
import pages.LoginPage;

public class M03_BookingWorkflowTests extends BaseTest {

    private static final Logger log = LogManager.getLogger(M02_HomeTests.class);

    private LoginPage loginPage;
    private BookingPage bookingPage;

    //------------------------------------------------------------------------------------------------------------------
    // 🛠️ Test Setup
    //------------------------------------------------------------------------------------------------------------------
    // Initializes page objects before running any test in this class.
    @BeforeMethod(alwaysRun = true)
    public void setUpPages() {
        loginPage = new LoginPage(mobileDriver);
        bookingPage = new BookingPage(mobileDriver);
        log.info("[INIT] Page objects initialized for LoginPage and BookingPage.");
    }

    //------------------------------------------------------------------------------------------------------------------
    // ✅ TC001 - Booking Form Input Fields Verification
    // Validates that all input fields in the booking form are functional and accept correct data,
    /**
     * Verifies:
     * - Booking form input fields are functional and accept correct data
     * - Date and Time pickers work as an expected
     * - Event type and amount fields accept input
     */
    //-------------------------------------------------------------------------
    @Test(priority = 1, description = "TC001 - Verifying booking form input fields and interactions", groups = {"sanity"})
    public void testBookingFormInputFields() throws InterruptedException {

        // Login first
        log.info("[TEST] Logging in with username 'test' and password '123456'");
        loginPage.enterUser("test");
        loginPage.enterPassword("123456");
        loginPage.clickLogin();
        loginPage.handleFingerprintPromptNo();

        // Navigate to booking page
        bookingPage.isHallLogoVisible();
        bookingPage.isTitleDisplayed();
        Thread.sleep(5000);

        // Tap on "Booking" to open the booking form
        log.info("[TEST] Navigating to Booking page and opening booking form.");
        bookingPage.tapBookingBtn();

        // Fill out the booking form with customer details, date, time, event type, and amounts
        log.info("[TEST] Filling out booking form with customer details, date, time, event type, and amounts.");
        bookingPage.enterCustomerName("Megan Fox");
        bookingPage.enterAddress("LA, San Fransisco");
        bookingPage.enterMobileNumber("8876543210");

        // Select date
        log.info("[TEST] Selecting date for booking.");
        bookingPage.tapDateField();                      // Tap on date field to open date picker
        bookingPage.tapDateEditIcon();                   // Tap on the edit icon to switch to manual date entry
        bookingPage.EnterSpecificDate("04/25/2026");     // Enter the date in MM/DD/YYYY format (modify as per your method's expected format)
        bookingPage.confirmDate();                       // Confirm the date selection (if needed, otherwise it may auto-confirm after entry)

        // Select time
        log.info("[TEST] Selecting time for booking.");
        bookingPage.openTimePicker();                    // Tap on time field to open time picker
        bookingPage.selectTime();                        // Tap on the edit icon to switch to manual time entry (if your app has this feature)
        //bookingPage.enterHours("10");                  // You need to modify your method to accept value
        //bookingPage.enterMinutes("30");
        bookingPage.selectAM();                          // You can either select AM/PM or enter time in 24-hour format, modify as per your app's implementation
        //bookingPage.selectPM();
        bookingPage.confirmTimeSelection();              // Confirm the time selection (if needed, otherwise it may auto-confirm after entry)

        // Event type
        log.info("[TEST] Entering event type for booking.");
        bookingPage.enterEventType("Party");             // Enter event type (e.g., "Wedding", "Conference", "Party")

        // Toggle and fill Catering
        //log.info("[TEST] Toggling catering option and entering details.");
        //bookingPage.toggleCatering();
        //log.info("[TEST] Entering catering details: Promised items and amount.");
        //bookingPage.enterPromisedItemsCatering("Veg Thali, Water");
        //bookingPage.enterAmountForCatering("12000");

        // Toggle and fill Decorations
        /*bookingPage.toggleDecorations();
        bookingPage.enterPromisedItemsDecorations("Stage Setup, Flowers");
        bookingPage.enterAmountForDecorations("8000");

        // Toggle and fill Photography
        bookingPage.togglePhotography();
        //bookingPage.enterAmountForPhotography("5000");

        // Toggle and fill Pandit
        bookingPage.togglePandit();
        //bookingPage.enterAmountForPandit("3000");*/

        // Enter hall and booking amount
        bookingPage.enterHallAmount("20000"); // Scroll to and enter the hall amount (modify as per your app's expected input)
        //bookingPage.enterHallAmount("20000");
        //Thread.sleep(5000);
        //bookingPage.enterBookingAmount("15000");          // Enter the booking amount (modify as per your app's expected input)

        // Fetch totals (optional: assert correctness)
        /*String total = bookingPage.getTotalAmount();
        String balance = bookingPage.getBalanceAmount();

        System.out.println("Total Amount: " + total);
        System.out.println("Balance Amount: " + balance);*/

        // Optionally confirm booking or send to WhatsApp
        // bookingPage.tapSendToWhatsApp();
        bookingPage.tapConfirmBooking();                // Tap on confirm booking button to finalize the booking (modify as per your app's flow)


    }

}

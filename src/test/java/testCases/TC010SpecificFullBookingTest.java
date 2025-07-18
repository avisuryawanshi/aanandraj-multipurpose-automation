package testCases;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BookingPage;
import pages.LoginPage;

public class TC010SpecificFullBookingTest extends BaseTest {


    private static final Logger log = LogManager.getLogger(TC010SpecificFullBookingTest.class);

// Positive Login Flow =================================================================================================

    @Test(priority = 0)
    public void testLoginWithSpecificCredentials() throws InterruptedException {
        log.info("===== Starting test: testLoginWithSpecificCredentials =====");

        LoginPage loginPage = new LoginPage(mobileDriver);

        // Positive Login Flow
        log.info("Step 1: Entering username");
        loginPage.enterUser("test");

        log.info("Step 2: Entering password");
        loginPage.enterPassword("123456");

        log.info("Step 3: Clicking login button");
        loginPage.clickLogin();

        log.info("Step 4: Handling fingerprint prompt (if shown/appears)");
        loginPage.handleFingerprintPromptNo();

        // log.info("Step 4: Accept fingerprint prompt (if shown/appears)");
        // loginPage.handleFingerprintPromptYes();
    }

    @Test(priority = 1, dependsOnMethods = {"testLoginWithSpecificCredentials"})
    public void testBookingFormInputFields() throws InterruptedException {

        BookingPage bookingPage = new BookingPage(mobileDriver);

        bookingPage.isHallLogoVisible();
        bookingPage.isTitleDisplayed();
        Thread.sleep(5000);
        bookingPage.tapBookingBtn();

        // Enter booking form details
        bookingPage.enterCustomerName("Nitin Gadkari");
        bookingPage.enterAddress("Mumbai, Maharashtra");
        bookingPage.enterMobileNumber("7896587853");

        // Enter date
        bookingPage.tapDateField();           // Open calendar
        bookingPage.tapDateEditIcon();        // Tap calendar/edit icon
        bookingPage.EnterSpecificDate("07/07/2025");   // Select "mm/dd/yyyy"
        bookingPage.confirmDate();            // Confirm the selection

        // Enter time
        bookingPage.openTimePicker();
        bookingPage.selectTime();
        bookingPage.enterHours("12");   // You need to modify your method to accept value
        bookingPage.enterMinutes("45"); // You need to modify your method to accept value
        bookingPage.selectAM(); // Select either AM or PM or Keep by default AM
        //bookingPage.selectPM();
        bookingPage.confirmTimeSelection();

        // Event type
        bookingPage.enterEventType("Meeting");

        // Toggle and fill Catering
        //bookingPage.toggleCatering();
        //bookingPage.enterPromisedItemsCatering("Veg Thali, Water");
        //bookingPage.enterAmountForCatering("12000");
        //int expectedAmount = 5500;
        //bookingPage.enterAmountForCatering(expectedAmount); // Action: enter amount

        // Toggle and fill Decorations
       /*bookingPage.toggleDecorations();
        bookingPage.enterPromisedItemsDecorations("Stage Setup, Flowers");
        bookingPage.enterAmountForDecorations("8000");

        // Toggle and fill Photography
        bookingPage.togglePhotography();
        //bookingPage.enterAmountForPhotography("5000");

        // Toggle and fill Pandit
        bookingPage.togglePandit();
        bookingPage.enterAmountForPandit("3000");

        // Enter hall and booking amount
        bookingPage.enterHallAmount("20000");
        Thread.sleep(5000);
        bookingPage.enterBookingAmount("15000");

        // Fetch totals (optional: assert correctness)
        String total = bookingPage.getTotalAmount();
        String balance = bookingPage.getBalanceAmount();

        System.out.println("Total Amount: " + total);
        System.out.println("Balance Amount: " + balance);

        // Optionally confirm booking or send to WhatsApp
        bookingPage.tapSendToWhatsApp();
        bookingPage.tapConfirmBooking();*/
    }
}


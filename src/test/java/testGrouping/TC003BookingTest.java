package testGrouping;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.BookingPage;
import pages.LoginPage;

public class TC003BookingTest extends BaseTest {

    /*public void initPages() {
        LoginPage loginPage = new LoginPage(mobileDriver);
        HomePage homePage = new HomePage(mobileDriver);
        BookingPage bookingPage = new BookingPage(mobileDriver);
    }*/

    @Test(priority = 0, groups= {"sanity","Master"})
    public void testLoginWithSpecificCredentials() {

        LoginPage loginPage = new LoginPage(mobileDriver);

        loginPage.enterUser("test");            // Step 1: Enter username
        loginPage.enterPassword("123456");      // Step 2: Enter password
        loginPage.clickLogin();                          // Step 3: Click login button
        loginPage.handleFingerprintPromptNo();           // Step 4: Handle fingerprint prompt (No)
        // loginPage.handleFingerprintPromptYes();       // Optional alternative
    }

    @Test(priority = 1, dependsOnMethods = {"testLoginWithSpecificCredentials"}, groups= {"sanity","Master"})
    public void testBookingFormInputFields() throws InterruptedException {

       // HomePage homePage = new HomePage(mobileDriver);
        BookingPage bookingPage = new BookingPage(mobileDriver);

        bookingPage.isHallLogoVisible();
        bookingPage.isTitleDisplayed();
        Thread.sleep(5000);
        bookingPage.tapBookingBtn();

        // Enter booking form details
        bookingPage.enterCustomerName("Megan Fox");
        bookingPage.enterAddress("LA, San Fransisco");
        bookingPage.enterMobileNumber("8876543210");

        // Enter date
        bookingPage.tapDateField();           // Open calendar
        bookingPage.tapDateEditIcon();        // Tap calendar/edit icon
        bookingPage.EnterSpecificDate("07/05/2025");   // Select "mm/dd/yyyy"
        bookingPage.confirmDate();            // Confirm the selection

        // Enter time
        bookingPage.openTimePicker();
        bookingPage.selectTime();
        bookingPage.enterHours("10");   // You need to modify your method to accept value
        bookingPage.enterMinutes("30"); // You need to modify your method to accept value
        //bookingPage.selectAM(); // Select either AM or PM or Keep by default AM
        bookingPage.selectPM();
        bookingPage.confirmTimeSelection();

        // Event type
        bookingPage.enterEventType("Party");

        // Toggle and fill Catering
        /*bookingPage.toggleCatering();
        bookingPage.enterPromisedItemsCatering("Veg Thali, Water");
        bookingPage.enterAmountForCatering("12000");*/

        // Toggle and fill Decorations
       /* bookingPage.toggleDecorations();
        bookingPage.enterPromisedItemsDecorations("Stage Setup, Flowers");
        bookingPage.enterAmountForDecorations("8000");

        // Toggle and fill Photography
        bookingPage.togglePhotography();
        //bookingPage.enterAmountForPhotography("5000");

        // Toggle and fill Pandit
        bookingPage.togglePandit();
        //bookingPage.enterAmountForPandit("3000");*/

        // Enter hall and booking amount
       // bookingPage.enterHallAmount("20000");
       // Thread.sleep(5000);
       //bookingPage.enterBookingAmount("15000");

        // Fetch totals (optional: assert correctness)
        /*String total = bookingPage.getTotalAmount();
        String balance = bookingPage.getBalanceAmount();

        System.out.println("Total Amount: " + total);
        System.out.println("Balance Amount: " + balance);*/

        // Optionally confirm booking or send to WhatsApp
        //bookingPage.tapSendToWhatsApp();
       // bookingPage.tapConfirmBooking();


    }

}

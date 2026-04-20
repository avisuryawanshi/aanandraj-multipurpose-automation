package testGrouping;

import base.DriverController;
import base.MultiDeviceBaseTest;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends MultiDeviceBaseTest {


        // ✅ Launch device-specific Appium driver

        /*@Test
        public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);

        // Test Steps
        loginPage.enterUser("test");
        loginPage.enterPassword("123456");
        loginPage.clickLogin();
        loginPage.handleFingerprintPromptNo();
    }*/
}

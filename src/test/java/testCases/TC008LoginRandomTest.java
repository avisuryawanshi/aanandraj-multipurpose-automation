package testCases;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.TestUtils;

public class TC008LoginRandomTest extends BaseTest{

	//------Generate random username and passcode-----------------------------------------------------------------
    @Test
    public void testLoginWithRandomCredentials() throws InterruptedException {
        LoginPage loginPage = new LoginPage(mobileDriver);

        String randomUsername = TestUtils.generateRandomUsername();
        String randomPasscode = TestUtils.generateRandomPasscode();

        System.out.println("Generated Username: " + randomUsername);
        System.out.println("Generated Passcode: " + randomPasscode);

        loginPage.enterUser(randomUsername);
        loginPage.enterPassword(randomPasscode);
        loginPage.clickLogin();
        loginPage.handleFingerprintPromptNo();  //Yes
        //loginPage.handleFingerprintPrompt(); //No


//----------Retrieve the hall text--------------------------------------------------------------------------------------
        //A sample test case that uses LoginPage to get the hall text and assert it matches the expected value.
        //String hallText = loginPage.getHallText();
        // Verify the retrieved text
        //Assert.assertEquals(hallText, "Anandraj Multipurpose Hall", "The text does not match the expected value.");
    }

}

package testCases;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.TestUtils;

public class TC007LoginBiometricTest extends BaseTest{
	
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
		        //loginPage.handleFingerprintPrompt();
		        loginPage.handleFingerprintPromptNo();
		 }

}

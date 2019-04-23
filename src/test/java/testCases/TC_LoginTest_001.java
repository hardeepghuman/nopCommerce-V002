package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass{
	@Test
	public void loginTest() throws InterruptedException, IOException {
		logger.info("*************** Started TC_LoginTest_001 ********* ");
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);
		logger.info("*************** Providing login info ********* ");
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickLogin();
		logger.info("Login in Clicked");//logger msg

		if (driver.getTitle().equals("Dashboard / nopCommerce administration")) {
			
			
			logger.info("***************Login is successfull********* ");
			Assert.assertTrue(true);
		} else {
			logger.error("***************Login is Failed********* ");
			captureScreen(driver,"loginTest"); //capturing screent on failure
			Assert.assertTrue(false);
		}
		logger.info("*************** Stopped TC_LoginTest_001 ********* ");
	}

}

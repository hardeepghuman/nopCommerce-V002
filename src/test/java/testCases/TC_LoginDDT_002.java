package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import utilities.XLUtils;

public class TC_LoginDDT_002  extends BaseClass{
	@Test(dataProvider="LoginData")
	public void loginTest(String user,String pwd) throws IOException 
	{
		logger.info("*************** Started TC_LoginTest_001 ********* ");
		driver.get(baseURL);
		LoginPage lp=new LoginPage(driver);
		
		logger.info("*************** Providing login info ********* ");
		lp.setUserName(user);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		if(driver.getTitle().equals("Dashboard / nopCommerce administration"))
		{
			logger.info("***************Login is successfull********* ");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("***************Login is Failed********* ");
			captureScreen(driver,"loginTest"); //capturing screent on failure
			Assert.assertTrue(false);
		}
		logger.info("*************** Completed TC_LoginTest_001 ********* ");
	
	}
	
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/testData/LoginData.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path,"Sheet1",1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1", i,j);
			}
		}
		
		return logindata;
		
	}


}

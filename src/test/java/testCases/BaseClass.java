package testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utilities.ReadConfig;

public class BaseClass {
	ReadConfig readconfig=new ReadConfig();
	public String baseURL= readconfig.getApplicationURL();
	public String username=readconfig.getUseremail();
	public String password=readconfig.getPassword();
	public WebDriver driver;
	public Logger logger; //Added logger
	@Parameters("browser")
	@BeforeClass
	public void setup( String br)
	
	{ 

	
		logger=Logger.getLogger("nopCommerce-V002");//Added logger
		
		PropertyConfigurator.configure("log4j.properties");//Added logger
		logger.setLevel(Level.DEBUG);
		if (br.equals("firefox")) {
			// Firefox Browser
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
		}

		else if (br.equals("chrome")) {
			// opens the browser
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			driver = new ChromeDriver();
		}
		
		else if (br.equals("ie")) {
			// opens the browser
			System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
			driver = new InternetExplorerDriver();
		}
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.close();
	}
	public void captureScreen(WebDriver driver, String tname) throws IOException  {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	public String randomestring() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}

	public String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
	
}

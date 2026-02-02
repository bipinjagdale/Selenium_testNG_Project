package testCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterClass;

public class BaseClass {
	
	public static WebDriver driver;
	
	public Logger logger;
	
	public Properties p;
	
	
	@SuppressWarnings("deprecation")
	@BeforeClass(groups={"Sanity","Regression", "Master" })
	@Parameters({"os", "browser"})
	void setup(String os, String br) throws IOException
	{	
		//loading config.propertires file
		
		FileReader file=new FileReader(".//src/test/resources/config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		if (p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			if (br.equalsIgnoreCase("chrome"))
			{
	            ChromeOptions options = new ChromeOptions();
	            options.setPlatformName(os.toLowerCase());
	            driver = new RemoteWebDriver(new URL(p.getProperty("grid_url")),options); 

	        }
			else if (br.equalsIgnoreCase("firefox"))
	        {
	            FirefoxOptions options = new FirefoxOptions();
	            options.setPlatformName(os.toLowerCase());
	            driver = new RemoteWebDriver(new URL(p.getProperty("grid_url")),options);      
	        } else if (br.equalsIgnoreCase("safari")) {
	            if (!os.equalsIgnoreCase("mac")) {throw new RuntimeException("Safari only supported on macOS");
	            }
	            SafariOptions options = new SafariOptions();
	            options.setPlatformName("mac");
	            driver = new RemoteWebDriver(new URL(p.getProperty("grid_url")),options);
	        } else {
	            throw new RuntimeException("Unsupported browser: " + br);
	        }
	    }

		
		//belwo code is for Selenium local execution
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{		
		switch(br.toLowerCase())
		{
		case "chrome": driver=new ChromeDriver();break;
		case "firefox": driver=new FirefoxDriver();break;
		case "safari": driver=new SafariDriver(); break;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
		}
		
	}
	
	
	@AfterMethod(groups={"Sanity","Regression", "Master" })
	@AfterClass
	@AfterSuite(alwaysRun=true)
	void tearDown()
	{
		driver.quit();
	}
	
	
	public String randomeString()
	{
		@SuppressWarnings("deprecation")
		String generatedstring= RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomeNumber()
	{
		@SuppressWarnings("deprecation")
		String generatednumber= RandomStringUtils.randomNumeric(5);
		return generatednumber;
	}
	

	public String randomeAplhaNumber()
	{
		@SuppressWarnings("deprecation")
		String generatedstring= RandomStringUtils.randomAlphabetic(5);
		@SuppressWarnings("deprecation")
		String generatednumber= RandomStringUtils.randomNumeric(5);
		return (generatedstring + "@" + generatednumber);
	}


	public String captureScreen(String tname) throws IOException {
		
		String timeStamp =new SimpleDateFormat("yyyyMMddHHss").format(new Date());
		
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath =System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}

}

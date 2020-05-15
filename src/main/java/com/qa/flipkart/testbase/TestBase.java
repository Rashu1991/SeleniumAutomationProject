package com.qa.flipkart.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.flipkart.testiutil.TestUtil;
import com.qa.flipkart.testiutil.WebEventListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver = null;
	public static String browserName = null;
	public static Properties prop;
	public static FileInputStream fis;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public ExtentReports extent;
	public ExtentTest extentTest;

	public TestBase() {
		prop = new Properties();
		try {
			//fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/flipkart/config/config.properties");
			//fis = new FileInputStream("C:\\Users\\Mudit\\eclipse-workspace\\POMAutomationPractice\\src\\main\\java\\com\\qa\\flipkart\\config\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@BeforeTest
	public void setExtent() {
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-ouputExtent/Extent.html",true);
		extent.addSystemInfo("Host Name", "Shobhit Windows 10");
		extent.addSystemInfo("User Name","Shobhit Varshney");
		extent.addSystemInfo("Enviornment","QA");
		System.out.println("Executed");
	}
	
	
	@AfterTest
	public void endReport() {
		
		extent.flush(); // flush the contents of the extent report
		extent.close(); // close the connection with extent report
	}

	public static void initialize(String browserName) {

		if (driver == null) {
			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("FF")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
		}
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public static void quit() {
		System.out.println("Quitting the browser");
		driver.quit();
		driver = null;
	}

	public static void close() {
		System.out.println("close the browser");
		driver.close();
		driver = null;
	}

	public static void launchUrl() {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		driver.get(prop.getProperty("url"));
	}
	
	@AfterMethod
	public void tearDownAll(ITestResult result) throws IOException {
		
		
		if(result.getStatus() == ITestResult.FAILURE) {
			// this is important without this we will get NullPOinterException
			// this method will return one ExtentTest object which will help in generating logs
			extentTest = extent.startTest("Tests", "Regrssion&SmokeTests");
			
			
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); // to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); // to add exception
			
			String screenshotPath=TestUtil.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL,extentTest.addScreencast(screenshotPath)); // to add screenshot in extent report
		}
		
		else if(result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "TEST CASE SKIPPED IS "+result.getName());
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "TEST CASE PASSED IS "+result.getName());
		}
		
		extent.endTest(extentTest); // ending test
		driver.quit();
		
		
	}	
}

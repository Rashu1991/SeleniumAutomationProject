package com.qa.flipkart.tests_smoke;


import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.qa.flipkart.pages.HomePage;
import com.qa.flipkart.pages.LoginPage;
import com.qa.flipkart.testbase.TestBase;
import com.qa.flipkart.testiutil.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	
	
	
	public LoginPageTest(){
		super();
	}

	
	@BeforeMethod
	public void init() {
		TestBase.initialize("chrome");
		TestBase.launchUrl();
		loginPage = new LoginPage();
	}
	
	@Test
	public  void login() {
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));		
	}
	
	
	 @AfterMethod
		public void tearDown() {
			
			TestBase.close();
		}
}

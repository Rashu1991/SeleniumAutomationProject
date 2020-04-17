package com.qa.flipkart.tests;

import org.apache.tools.ant.taskdefs.WaitFor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.flipkart.pages.HomePage;
import com.qa.flipkart.pages.LoginPage;
import com.qa.flipkart.testbase.TestBase;
import com.qa.flipkart.testiutil.TestUtil;

import constantsUtil.Constants;

public class GroceryPageTest extends TestBase {
	HomePage homePage;
	LoginPage loginPage;
	
	public GroceryPageTest(){
		
		super();
	}

	@BeforeMethod
	public void setUp() {
			TestBase.initialize(prop.getProperty("browser"));
			TestBase.launchUrl();
			loginPage= new LoginPage();
			homePage = new HomePage();
	}
	
	@Test
	public void verifyTitle() {
		 homePage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		 TestUtil.waitForPageLoad(driver);
		 homePage.verifyGroferSubMenu();
		 //homePage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		 driver.navigate().to(Constants.GROCERY_PAGE_URL);
		 System.out.println(driver.getTitle());
		 Assert.assertEquals(driver.getTitle(), Constants.GROCERY_PAGE_TITLE,"Grocery Page Title not verified");
	}
	
	
	@AfterMethod
	public void tearDown() {
		TestBase.close();
	}
	
}

package com.qa.flipkart.tests_smoke;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;


import com.qa.flipkart.pages.GroceryPage;
import com.qa.flipkart.pages.HomePage;
import com.qa.flipkart.pages.LoginPage;
import com.qa.flipkart.pages.OffersPage;
import com.qa.flipkart.testbase.TestBase;
import com.qa.flipkart.testiutil.TestUtil;

import constantsUtil.Constants;

public class HomePageTest extends TestBase {
	

	HomePage homePage;
	LoginPage loginPage;
	GroceryPage groceryPage;
	OffersPage offersPage;
	
	
			
	public HomePageTest() {
		super();
	}

	
	@BeforeMethod
	public void setUp() {
		TestBase.initialize("chrome");
		TestBase.launchUrl();
		homePage = new HomePage();
		loginPage = new LoginPage();
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
	}

	@Test
	public void verifyHomePageTitle() {
		Assert.assertEquals(driver.getTitle(), Constants.HOME_PAGE_TITLE, "Title does not match");
	}

	@Test
	public void verifyAppUrl() {

		Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("url"), "Url does not match");
	}

	@Test
	public void clickOnSearchBar() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TestUtil.waitForPageLoad(driver);
		homePage.doSearchBar("");
	}

	@Test
	public void getMenuBarList(){
		//Thread.sleep(5000);
		TestUtil.waitForPageLoad(driver);
		homePage.doGetMenuBarList();
	}

	
	  @Test
	  public void doLogout(){
	 
	  TestUtil.waitForPageLoad(driver);
	  try {
		Thread.sleep(Constants.STATIC_TIMEOUT);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  homePage.doGetMenuBarList(0);
	  homePage.logout();
	  }
	 
//	 @Test
//	 public void getAllLinksVeriyTest() {
//		 try {
//			homePage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
//			homePage.getAllLinksVerify();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	 }
	 
	@Test
	 public void verifySubMenuBarListTest() {
			//Thread.sleep(Constants.STATIC_TIMEOUT);
			TestUtil.waitForPageLoad(driver);
			homePage.verifySubMenuBarList();
	 }

	
	
	 @AfterMethod
	public void tearDown() {
		
		TestBase.close();
	}
	
	
}

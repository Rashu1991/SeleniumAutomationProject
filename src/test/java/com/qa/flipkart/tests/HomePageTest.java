package com.qa.flipkart.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.qa.flipkart.pages.HomePage;
import com.qa.flipkart.pages.LoginPage;
import com.qa.flipkart.testbase.TestBase;
import com.qa.flipkart.testiutil.TestUtil;

import constantsUtil.Constants;

public class HomePageTest extends TestBase {
	
	//What is log? : capturing info/activities at the time of program execution. 
		// types of logs:
			//1. info
			//2. warn
			//3. debug
			//4. fatal
			
		//how to generate the logs? : use Apache log4j API (log4j jar)
		//How it works? : it reads log 4j configuration from log4j.properties file
		//where to create: create inside resources folder

	HomePage homePage;
	LoginPage loginPage;
	
	
			
	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		TestBase.initialize(prop.getProperty("browser"));
		TestBase.launchUrl();
		homePage = new HomePage();
		loginPage = new LoginPage();
		
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
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		//Thread.sleep(3000);
		driver.navigate().refresh();
		TestUtil.waitForPageLoad(driver);
		
		homePage.doSearchBar();
	}

	@Test
	public void getMenuBarList(){
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		//Thread.sleep(5000);
		TestUtil.waitForPageLoad(driver);
		homePage.doGetMenuBarList();
	}

	
	  @Test
	  public void doLogout() throws InterruptedException {
	  homePage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
	  //Thread.sleep(Constants.STATIC_TIMEOUT);
	  Assert.assertEquals(false, true);
	  TestUtil.waitForPageLoad(driver);
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
			homePage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
			//Thread.sleep(Constants.STATIC_TIMEOUT);
			TestUtil.waitForPageLoad(driver);
			homePage.verifySubMenuBarList();
	 }

	 @Test
	 public void goToGroceryPageTest() {
		 homePage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		 homePage.verifyGroferSubMenu();
	 }
	 
	 @Test
	 public void goToOffersPageTest() {
		 homePage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		 homePage.verifyOfferZoneSubMenu();
	 }
	@AfterMethod
	public void tearDown() {
		
		TestBase.close();
	}
}

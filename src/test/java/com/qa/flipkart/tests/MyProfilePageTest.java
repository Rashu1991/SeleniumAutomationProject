package com.qa.flipkart.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.flipkart.pages.HomePage;
import com.qa.flipkart.pages.LoginPage;
import com.qa.flipkart.testbase.TestBase;
import com.qa.flipkart.testiutil.TestUtil;

import constantsUtil.Constants;

public class MyProfilePageTest extends TestBase{
	
	HomePage homePage;
	LoginPage loginPage;
	
	public MyProfilePageTest() {
		// TODO Auto-generated constructor stub
		
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
	public void verifyMyProfilePageTitleTest() {
		
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		 TestUtil.waitForPageLoad(driver);
		 try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 homePage.doGetMenuBarList(0);
		 homePage.clickOnMyAccounts_MyProfile();
		 driver.navigate().to(Constants.MYPROFILE_PAGE_URL);
		 System.out.println();
		 Assert.assertEquals(driver.getTitle(), Constants.MYPROFILE_PAGE_TITLE,"My Profile Page title not verified");
		 
		
	}
	
	@AfterMethod
	public void tearDown() {
		TestBase.close();
	}

}

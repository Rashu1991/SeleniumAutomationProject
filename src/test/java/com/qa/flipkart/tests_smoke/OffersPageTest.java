package com.qa.flipkart.tests_smoke;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.flipkart.pages.HomePage;
import com.qa.flipkart.pages.LoginPage;
import com.qa.flipkart.testbase.TestBase;
import com.qa.flipkart.testiutil.TestUtil;

import constantsUtil.Constants;

public class OffersPageTest extends TestBase{
	HomePage homePage;
	LoginPage loginPage;
	public OffersPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		TestBase.initialize("chrome");
		TestBase.launchUrl();
		homePage = new HomePage();
		loginPage = new LoginPage();
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		TestUtil.waitForPageLoad(driver);
		
	}
	@Test
	public void verifyOffersPageTitle() {
		
		 homePage.verifyOfferZoneSubMenu();
		 driver.navigate().to(Constants.OFFERS_PAGE_URL);
		 System.out.println(driver.getTitle());
		 Assert.assertEquals(driver.getTitle(), Constants.OFFERS_PAGE_TITLE,"Offer Page Title not verified");
	}
	
	@AfterMethod
	public void tearDown() {
		TestBase.close();
	}
}

package com.qa.flipkart.tests;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.flipkart.pages.HomePage;
import com.qa.flipkart.pages.LoginPage;
import com.qa.flipkart.testbase.TestBase;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	
	
	
	public LoginPageTest(){
		super();
	}

	@BeforeMethod
	public void init() {
		TestBase.initialize(prop.getProperty("browser"));
		TestBase.launchUrl();
		loginPage = new LoginPage();
	}
	
	@Test
	public  void login() {
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@AfterMethod
	public void tearDownAll() {
		TestBase.quit();
	}	
	
}

package com.qa.flipkart.tests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
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

	@Parameters("Browser1")
	@BeforeMethod
	public void init(String browser) {
		TestBase.initialize(browser);
		//TestBase.initialize(prop.getProperty("browser"));
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

package com.qa.flipkart.tests_regression;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.flipkart.pages.LoginPage;
import com.qa.flipkart.testbase.TestBase;

import constantsUtil.Constants;

public class LoginPageTest extends TestBase {
	LoginPage loginPage;
	public LoginPageTest() {
		super();
	}

	//@Parameters("Browser1")
	@BeforeMethod
	public void setUp() {
		TestBase.initialize(prop.getProperty("browser"));
		TestBase.launchUrl();
		loginPage = new LoginPage();
	}
	
	@Test
	public void loginValidTest() {
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test
	public void loginForgotPassChangeEmailTest() {
		boolean isClicked=loginPage.loginForgotPassChangeEmail(prop.getProperty("username"));
		Assert.assertTrue(isClicked,"Not clicked on the Forgot and or Change Email links");
		
	}
	
	@Test
	public void loginForgotPassResendOTPTest() {
		
		boolean isClicked=loginPage.loginForgotPassResendOTP(prop.getProperty("username"));
		Assert.assertTrue(isClicked,"Not clicked on the Forgot and or Resend otp links");
	}
	
	@Test
	public void loginInValidUsernameTest() {
		String errMsg=loginPage.loginInvalidUsername(Constants.INVALID_USERNAME);
		Assert.assertEquals(errMsg, "Please enter valid Email ID/Mobile number");
		
	}
	
	@Test
	public void loginInValidPassTest() {
		String errMsg=loginPage.loginInvalidPass(Constants.INVALID_PASSWORD);
		Assert.assertEquals(errMsg, "Please enter valid Email ID/Mobile number");
	}
	
	@Test
	public void LoginValidUsernameBlankPassTest() {
		String errMsg=loginPage.loginValidUsernameBlankPass(prop.getProperty("username"), "");
		Assert.assertEquals(errMsg, "Please enter Password");
		
		
	}
	
	@Test
	public void loginValidUsernameAndInvalidPassTest() {
		String errMsgloginPageString=loginPage.loginInValidUsernameAndPass(prop.getProperty("username"), Constants.INVALID_PASSWORD);
		Assert.assertEquals(errMsgloginPageString, "Your username or password is incorrect");
		
	}
	@AfterMethod
	public void tearDownAll() {
		TestBase.quit();
	}
}
